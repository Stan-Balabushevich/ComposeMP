package id.slavnt.composemp

import android.app.Application
import id.slavnt.composemp.di.KoinInitializer

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val koinInit = KoinInitializer(this)

        if (!koinInit.isKoinStarted()) {
            koinInit.startKoinIfNeeded()
        }

//        startKoin {
//
//            androidContext(this@MyApplication)
//            modules(
//                platformModule,
//                presentationModule
//            )
//        }
    }
}