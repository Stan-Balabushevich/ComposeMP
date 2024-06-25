package id.slavnt.composemp

import android.app.Application
import id.slavnt.composemp.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MyApplication)
            modules(
                platformModule
            )
        }
    }
}