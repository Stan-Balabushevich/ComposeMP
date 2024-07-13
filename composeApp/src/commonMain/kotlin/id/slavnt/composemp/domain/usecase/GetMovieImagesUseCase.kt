package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.domain.repository.MoviesRepository

class GetMovieImagesUseCase(private val repository: MoviesRepository){

    suspend operator fun invoke(movieId: Int) = repository.getMovieImages(movieId)

}