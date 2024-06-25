package id.slavnt.composemp.di

import database.PeopleDatabase
import id.slavnt.composemp.database.getPeopleDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<PeopleDatabase> {
            getPeopleDatabase(context = get())
        }
    }