package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.domain.repository.MoviesRepository

class GetMoviesUseCase(private val repository: MoviesRepository){

    suspend operator fun invoke() = repository.getMovies()


}