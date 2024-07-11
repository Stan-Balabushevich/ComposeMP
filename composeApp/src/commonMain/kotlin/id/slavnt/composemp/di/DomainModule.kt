package id.slavnt.composemp.di

import id.slavnt.composemp.domain.usecase.GetMovieByIdUseCase
import id.slavnt.composemp.domain.usecase.GetMovieReviewsUseCase
import id.slavnt.composemp.domain.usecase.GetMovieVideosUseCase
import id.slavnt.composemp.domain.usecase.GetPopMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetTopRatedMoviesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetPopMoviesUseCase(repository = get()) }

    factory { GetTopRatedMoviesUseCase(repository = get()) }

    factory { GetMovieByIdUseCase(repository = get()) }

    factory { GetMovieReviewsUseCase(repository = get()) }

    factory { GetMovieVideosUseCase(repository = get()) }



}