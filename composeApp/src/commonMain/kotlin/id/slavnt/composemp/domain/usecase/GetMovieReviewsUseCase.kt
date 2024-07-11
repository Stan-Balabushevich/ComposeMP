package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.domain.repository.MoviesRepository

class GetMovieReviewsUseCase(private val repository: MoviesRepository){

    suspend operator fun invoke(movieId: Int) = repository.getMovieReviews(movieId)


}