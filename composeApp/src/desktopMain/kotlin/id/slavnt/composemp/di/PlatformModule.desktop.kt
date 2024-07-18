package id.slavnt.composemp.di

import BatteryManager
import id.slavnt.composemp.data.local.database.MovieDatabase
import id.slavnt.composemp.data.local.database.getMovieDatabase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<MovieDatabase> {
            getMovieDatabase()
        }
//        single { BatteryManager() }
        singleOf(::BatteryManager)
    }
