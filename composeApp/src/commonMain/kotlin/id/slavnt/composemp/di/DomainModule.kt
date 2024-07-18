package id.slavnt.composemp.di

import id.slavnt.composemp.domain.usecase.AddFavoriteMovieUseCase
import id.slavnt.composemp.domain.usecase.CheckFavoriteMovieUseCase
import id.slavnt.composemp.domain.usecase.GetFavoritesMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetMovieByIdUseCase
import id.slavnt.composemp.domain.usecase.GetMovieCreditsUseCase
import id.slavnt.composemp.domain.usecase.GetMovieImagesUseCase
import id.slavnt.composemp.domain.usecase.GetMovieReviewsUseCase
import id.slavnt.composemp.domain.usecase.GetMovieVideosUseCase
import id.slavnt.composemp.domain.usecase.GetPopMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetTopRatedMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetUpcomingMoviesUseCase
import id.slavnt.composemp.domain.usecase.RemoveFavoriteMovieUseCase
import id.slavnt.composemp.domain.usecase.SearchMovieUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetPopMoviesUseCase(repository = get()) }

    factory { GetTopRatedMoviesUseCase(repository = get()) }

    factory { GetMovieByIdUseCase(repository = get()) }

    factory { GetMovieReviewsUseCase(repository = get()) }

    factory { GetMovieVideosUseCase(repository = get()) }

    factory { SearchMovieUseCase(repository = get()) }

    factory { GetUpcomingMoviesUseCase(repository = get()) }

    factory { GetMovieImagesUseCase(repository = get()) }

    factory { GetMovieCreditsUseCase(repository = get()) }

    factory { GetFavoritesMoviesUseCase(repository = get()) }

    factory { CheckFavoriteMovieUseCase(repository = get()) }

    factory { AddFavoriteMovieUseCase(repository = get()) }

    factory { RemoveFavoriteMovieUseCase(repository = get()) }

}