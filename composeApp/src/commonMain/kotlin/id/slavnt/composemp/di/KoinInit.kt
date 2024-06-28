package id.slavnt.composemp.di

expect class KoinInitializer {
    fun startKoinIfNeeded()
    fun isKoinStarted(): Boolean
}
