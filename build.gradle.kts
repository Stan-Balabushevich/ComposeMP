plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

//subprojects {
//    afterEvaluate {
//        if (plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.library")) {
//            extensions.configure<com.android.build.gradle.BaseExtension> {
//                compileSdkVersion(libs.versions.android.compileSdk.get().toInt())
//                defaultConfig {
//                    minSdkVersion(libs.versions.android.minSdk.get().toInt())
//                    targetSdkVersion(libs.versions.android.targetSdk.get().toInt())
//                }
//                compileOptions {
//                    sourceCompatibility = JavaVersion.VERSION_11
//                    targetCompatibility = JavaVersion.VERSION_11
//                }
//            }
//        }
//
//        if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
//            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//                kotlinOptions {
//                    jvmTarget = "11"
//                }
//            }
//        }
//    }
//}