package id.slavnt.composemp.di

import BatteryManager
import id.slavnt.composemp.data.local.database.MovieDatabase
import id.slavnt.composemp.data.local.database.getMovieDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
        single<MovieDatabase> {
            getMovieDatabase(context = get())

        }

//    single { getPeopleDatabase(context = get()) }.bind<PeopleDatabase>()
//        single { BatteryManager(androidContext()) }
    singleOf(::BatteryManager)

    }