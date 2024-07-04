package id.slavnt.composemp.di

import id.slavnt.composemp.data.remote.MovieApiService
import id.slavnt.composemp.data.remote.MovieApiServiceImpl
import id.slavnt.composemp.data.remote.httpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    single { httpClient }

    single<MovieApiService> { MovieApiServiceImpl(client = get())  }

//    singleOf(::MovieApiServiceImpl)

}