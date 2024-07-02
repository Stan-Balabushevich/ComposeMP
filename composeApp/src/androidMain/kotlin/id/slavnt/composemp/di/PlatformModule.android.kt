package id.slavnt.composemp.di

import BatteryManager
import database.PeopleDatabase
import id.slavnt.composemp.database.getPeopleDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
        single<PeopleDatabase> {
            getPeopleDatabase(context = get())

        }

//    single { getPeopleDatabase(context = get()) }.bind<PeopleDatabase>()


//        single { BatteryManager(androidContext()) }

    singleOf(::BatteryManager)

    }