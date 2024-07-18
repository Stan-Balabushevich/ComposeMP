package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.repository.MoviesRepository

class RemoveFavoriteMovieUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieMainItem) = repository.removeFavoriteMovie(movie)

}