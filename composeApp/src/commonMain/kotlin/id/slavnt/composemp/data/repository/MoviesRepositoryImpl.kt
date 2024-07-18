package id.slavnt.composemp.data.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.local.database.MovieDatabase
import id.slavnt.composemp.data.local.database.db_object.toMovieEntity
import id.slavnt.composemp.data.local.database.db_object.toMovieItem
import id.slavnt.composemp.data.remote.MovieApiService
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.data.remote.dt_object.toMovieCastModel
import id.slavnt.composemp.data.remote.dt_object.toMovieDetailModel
import id.slavnt.composemp.data.remote.dt_object.toMovieImageModel
import id.slavnt.composemp.data.remote.dt_object.toMovieItem
import id.slavnt.composemp.data.remote.dt_object.toMovieReviewsModel
import id.slavnt.composemp.data.remote.dt_object.toMovieVideoModel
import id.slavnt.composemp.data.remote.dt_object.toMoviesModel
import id.slavnt.composemp.domain.models.MovieCastModel
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieImageModel
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.models.MovieReviewModel
import id.slavnt.composemp.domain.models.MovieVideoModel
import id.slavnt.composemp.domain.models.MoviesModel
import id.slavnt.composemp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val apiService: MovieApiService,
    private val db: MovieDatabase
): MoviesRepository {


    override suspend fun getPopMovies(page: Int): Flow<Resource<MoviesModel>> = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.getPopMovies(page).toMoviesModel()
            emit(Resource.Success(movies))

    } catch (e: Exception) {

        emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getTopRatedMovies(page: Int): Flow<Resource<MoviesModel>>  = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.getTopRatedMovies(page).toMoviesModel()
            emit(Resource.Success(movies))

        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun getUpcomingMovies(page: Int): Flow<Resource<MoviesModel>> = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.getUpcomingMovies(page).toMoviesModel()
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

    override suspend fun searchMovie(query: String, page: Int): Flow<Resource<MoviesModel>> = flow {
        emit(Resource.Loading())

        try {
            val movies = apiService.searchMovie(query,page).toMoviesModel()
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

    override suspend fun getFavoriteMovies(): Flow<Resource<List<MovieMainItem>>> = flow {
        emit(Resource.Loading())

        try {
             db.movieDao().getAllMovies()
                 .map { movieEntities ->
                     movieEntities.map { it.toMovieItem() }
                 }.collect{
                     movies ->
                     emit(Resource.Success(movies))
            }
        } catch (e: Exception) {

            emit(Resource.Error(e.message ?: "An error occurred"))

        }
    }

    override suspend fun addFavoriteMovie(movie: MovieMainItem) {
        db.movieDao().upsert(movie.toMovieEntity())
    }

    override suspend fun removeFavoriteMovie(movie: MovieMainItem) {
        db.movieDao().delete(movie.toMovieEntity())
    }

    override suspend fun checkFavoriteMovie(movieId: Int): Boolean =
        db.movieDao().getMovieById(movieId) != null


}