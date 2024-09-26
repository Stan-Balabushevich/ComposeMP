import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {

    /**
     * Why Adding the Source Directory Works
     *
     *     KSP Generates Code in Specific Directories:
     *         KSP generates the necessary code for annotations into specific directories within the build directory.
     *         For the commonMain source set, it generates metadata files in build/generated/ksp/metadata.
     *
     *     Including Generated Code in Source Sets:
     *         By default, the generated code is not included in your source sets.
     *         Therefore, your build system does not know where to find the generated classes (e.g., PeopleDatabase_Impl).
     *
     *     Making Generated Code Available to the Compiler:
     *         When you explicitly add build/generated/ksp/metadata to your source sets, you are telling the Kotlin compiler
     *         where to find the generated files. This ensures that the generated implementations of your Room
     *         database and DAOs are available during compilation.
     * **/

    sourceSets.commonMain {
        kotlin.srcDir("build/generated/ksp/metadata")
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.room.runtime.android)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            implementation(libs.ktor.client.okhttp)
            implementation (libs.ktor.client.android)

            // Android you tube player
            implementation (libs.androidyoutubeplayer)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))
            implementation(libs.assertk)
            implementation(libs.kotlinx.coroutines.test)

            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            //room
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            //koin
            // Using api: If you declare a dependency with api in commonMain, the dependency will be available
            // not only in commonMain but also in all platform-specific source sets that depend on commonMain.
            // This means you don’t need to redeclare it in platform-specific modules.
//            api(libs.koin.core)
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)

            //ktor
//            api(libs.ktor.core)
            implementation(libs.bundles.ktor)

            //coil
            implementation(libs.bundles.coil)

            //common viewmodel
            implementation(libs.lifecycle.viewmodel.compose)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.koin.core)

            implementation(libs.kotlinx.coroutines.core) // Needed for basic coroutine support
            implementation(libs.kotlinx.coroutines.swing) // Needed for Swing-specific coroutine support for desktop

            implementation(libs.ktor.client.okhttp)
        }
        nativeMain.dependencies {
            implementation(libs.koin.core)
            implementation(libs.ktor.client.darwin)
            implementation(libs.kotlinx.serialization.json)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "id.slavnt.composemp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "id.slavnt.composemp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "id.slavnt.composemp"
            packageVersion = "1.0.0"
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

/**
 *     Using add("kspCommonMainMetadata", libs.room.compiler) is a more explicit and controlled way to add dependencies to a specific configuration.
 *     This method avoids issues with configuration locking because it specifically targets the kspCommonMainMetadata configuration, which is intended for KSP.
 *
 * Why the add Method Works:
 *
 *     Configuration Specificity: The add method targets a specific configuration (kspCommonMainMetadata),
 *     ensuring that it doesn't interfere with other configurations that might already be locked.
 *     Dependency Resolution Order: By explicitly adding the dependency to kspCommonMainMetadata,
 *     you avoid potential conflicts with other parts of the build script that might be resolving configurations at different stages.
 *
 *     Using add("kspCommonMainMetadata", libs.room.compiler) instead of ksp(libs.room.compiler) ensures that the dependencies
 *     are added to the correct configuration without causing conflicts. This method is
 *     particularly useful in a Kotlin Multiplatform project where configurations are more complex and
 *     need precise control to avoid issues with configuration locking.
 * **/

dependencies {
    // Room
    add("kspCommonMainMetadata", libs.room.compiler)
//    ksp(libs.room.compiler)
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
