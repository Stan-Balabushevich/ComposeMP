package id.slavnt.composemp.di

import id.slavnt.composemp.data.remote.MovieApiService
import id.slavnt.composemp.data.remote.MovieApiServiceImpl
import id.slavnt.composemp.data.remote.httpClient
import id.slavnt.composemp.data.remote.repository.MoviesRepositoryImpl
import id.slavnt.composemp.domain.repository.MoviesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    single { httpClient }

    single<MovieApiService> { MovieApiServiceImpl(client = get())  }

    single<MoviesRepository>{ MoviesRepositoryImpl(apiService = get()) }

//    singleOf(::MovieApiServiceImpl)

}