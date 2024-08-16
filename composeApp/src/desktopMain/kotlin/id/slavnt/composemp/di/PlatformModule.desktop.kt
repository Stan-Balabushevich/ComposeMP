package id.slavnt.composemp.di

import id.slavnt.composemp.data.local.database.MovieDatabase
import id.slavnt.composemp.data.local.database.getMovieDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<MovieDatabase> {
            getMovieDatabase()
        }
    }
