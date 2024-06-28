package id.slavnt.composemp.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

actual class KoinInitializer(private val context: Context) {
    private var isStarted = false

    actual fun startKoinIfNeeded() {
        if (!isStarted) {
            startKoin {
                androidContext(context)  // Android-specific context
                modules(listOf(presentationModule, platformModule))
            }
            isStarted = true
        }
    }

    actual fun isKoinStarted(): Boolean = isStarted
}
