package id.slavnt.composemp.data.remote.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.MovieApiService
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.data.remote.dt_object.toMovieDetailModel
import id.slavnt.composemp.data.remote.dt_object.toMovieItem
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val apiService: MovieApiService): MoviesRepository {


    override suspend fun getPopMovies(page: Int): Flow<Resource<Movies>> = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.getPopMovies(page)
            emit(Resource.Success(movies))

    } catch (e: Exception) {

        emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getTopRatedMovies(page: Int): Flow<Resource<Movies>>  = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.getTopRatedMovies(page)
            emit(Resource.Success(movies))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getMovieById(movieId: Int): Flow<Resource<MovieDetailModel>> = flow {
        emit(Resource.Loading())

        try {
            val movie = apiService.getMovieDetail(movieId)
            emit(Resource.Success(movie.toMovieDetailModel()))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }
}