package id.slavnt.composemp.di

import id.slavnt.composemp.data.remote.MovieApiService
import id.slavnt.composemp.data.remote.MovieApiServiceImpl
import id.slavnt.composemp.data.repository.MoviesRepositoryImpl
import id.slavnt.composemp.domain.repository.MoviesRepository
import org.koin.dsl.module

val dataModule = module {

//    single { httpClient }

    single<MovieApiService> { MovieApiServiceImpl(client = get())  }

    single<MoviesRepository>{ MoviesRepositoryImpl(apiService = get(), db = get()) }

//    singleOf(::MovieApiServiceImpl)

}