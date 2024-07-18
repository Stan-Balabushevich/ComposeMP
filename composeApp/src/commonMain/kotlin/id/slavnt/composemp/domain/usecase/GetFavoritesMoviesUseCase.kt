package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.domain.repository.MoviesRepository

class GetFavoritesMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke() = repository.getFavoriteMovies()

}