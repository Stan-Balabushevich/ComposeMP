package id.slavnt.composemp.di

import id.slavnt.composemp.data.local.database.MovieDatabase
import id.slavnt.composemp.data.local.database.getMovieDatabase
import id.slavnt.composemp.data.remote.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule = module {

        single<MovieDatabase> {
            getMovieDatabase(context = get())
        }

    single { createHttpClient(OkHttp.create()) }

    }