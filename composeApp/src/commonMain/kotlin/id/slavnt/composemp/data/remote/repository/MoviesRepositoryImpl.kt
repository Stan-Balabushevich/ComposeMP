package id.slavnt.composemp.data.remote.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.MovieApiService
import id.slavnt.composemp.data.remote.dt_object.toMovieItem
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val apiService: MovieApiService): MoviesRepository {


    override suspend fun getMovies(): Flow<Resource<List<MovieMainItem>>> = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.getPopMovies().results.map { it.toMovieItem() }
            emit(Resource.Success(movies))

    } catch (e: Exception) {

        emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }
}