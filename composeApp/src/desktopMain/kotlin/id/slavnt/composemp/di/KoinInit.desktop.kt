package id.slavnt.composemp.di

import org.koin.core.context.startKoin

actual class KoinInitializer {
    private var isStarted = false

    actual fun startKoinIfNeeded() {
        if (!isStarted) {
            startKoin {
                modules(listOf(presentationModule, platformModule))
            }
            isStarted = true
        }
    }

    actual fun isKoinStarted(): Boolean = isStarted

}
