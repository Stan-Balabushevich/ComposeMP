package id.slavnt.composemp.di

import id.slavnt.composemp.data.local.database.MovieDatabase
import id.slavnt.composemp.data.local.database.getMovieDatabase
import id.slavnt.composemp.data.remote.createHttpClient
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<MovieDatabase> {
            getMovieDatabase()
        }

        single {
            createHttpClient(Darwin.create())
        }

    }