package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.domain.repository.MoviesRepository

class GetUpcomingMoviesUseCase(private val repository: MoviesRepository){

    suspend operator fun invoke(page: Int) = repository.getUpcomingMovies(page)


}