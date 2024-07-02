package id.slavnt.composemp.di

import BatteryManager
import database.PeopleDatabase
import database.getPeopleDatabase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<PeopleDatabase> {
            getPeopleDatabase()
        }
//        single { BatteryManager() }
        singleOf(::BatteryManager)
    }
