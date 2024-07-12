package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.domain.repository.MoviesRepository

class SearchMovieUseCase(private val repository: MoviesRepository){

    suspend operator fun invoke(query: String,page: Int) = repository.searchMovie(query, page)


}