package id.slavnt.composemp

import android.app.Application
import id.slavnt.composemp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@MyApplication)
        }

    }
}