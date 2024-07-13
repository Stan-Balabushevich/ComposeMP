package id.slavnt.composemp.data.remote.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.MovieApiService
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.data.remote.dt_object.toMovieCastModel
import id.slavnt.composemp.data.remote.dt_object.toMovieDetailModel
import id.slavnt.composemp.data.remote.dt_object.toMovieImageModel
import id.slavnt.composemp.data.remote.dt_object.toMovieItem
import id.slavnt.composemp.data.remote.dt_object.toMovieReviewsModel
import id.slavnt.composemp.data.remote.dt_object.toMovieVideoModel
import id.slavnt.composemp.domain.models.MovieCastModel
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieImageModel
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.models.MovieReviewModel
import id.slavnt.composemp.domain.models.MovieVideoModel
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

    override suspend fun getUpcomingMovies(page: Int): Flow<Resource<Movies>> = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.getUpcomingMovies(page)
            emit(Resource.Success(movies))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }


    override suspend fun getLatestMovie(): Flow<Resource<MovieMainItem>> = flow {
        emit(Resource.Loading())

        try {
            val movie = apiService.getLatestMovie().toMovieItem()
            emit(Resource.Success(movie))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun searchMovie(query: String, page: Int): Flow<Resource<Movies>> = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.searchMovie(query,page)
            emit(Resource.Success(movies))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getMovieById(movieId: Int): Flow<Resource<MovieDetailModel>> = flow {
        emit(Resource.Loading())

        try {
            val movie = apiService.getMovieDetail(movieId).toMovieDetailModel()
            emit(Resource.Success(movie))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getMovieReviews(movieId: Int): Flow<Resource<MovieReviewModel>> = flow {
        emit(Resource.Loading())

        try {
            val movieReviews = apiService.getMovieReviews(movieId).toMovieReviewsModel()
            emit(Resource.Success(movieReviews))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideoModel>>> = flow {
        emit(Resource.Loading())

        try {
            val videoList = apiService.getMovieVideos(movieId).results.map { it.toMovieVideoModel() }
            emit(Resource.Success(videoList))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getMovieCredits(movieId: Int): Flow<Resource<List<MovieCastModel>>>  = flow {
        emit(Resource.Loading())

        try {
            val credits = apiService.getMovieCredits(movieId).cast.map { it.toMovieCastModel() }
            emit(Resource.Success(credits))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getMovieImages(movieId: Int): Flow<Resource<List<MovieImageModel>>> = flow {
        emit(Resource.Loading())

        try {
            val images = apiService.getMovieImages(movieId).backdrops.map { it.toMovieImageModel() }
            emit(Resource.Success(images))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

}