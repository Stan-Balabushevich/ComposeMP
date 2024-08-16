package id.slavnt.composemp.data.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.local.database.MovieDatabase
import id.slavnt.composemp.data.local.database.db_object.toMovieEntity
import id.slavnt.composemp.data.local.database.db_object.toMovieItem
import id.slavnt.composemp.data.remote.MovieApiService
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl(
    private val apiService: MovieApiService,
    private val db: MovieDatabase
): MoviesRepository {


    /**
     * Explanation:
     *     Flow Emission: You emit a Loading state first, then you proceed to fetch the movies from the API.
     *     Success Handling: If the API call is successful, you emit a Success state with the movies data.
     *     Error Handling: If an exception occurs at any point in the flow, the catch block will handle it and emit an Error state.
     * This approach ensures that your flow will not attempt to emit a value after an exception has been thrown,
     * which would otherwise lead to a Flow exception transparency is violated error.
     */
    override suspend fun getPopMovies(page: Int): Flow<Resource<MoviesModel>> = flow {

        emit(Resource.Loading())
        val movies = apiService.getPopMovies(page).toMoviesModel()
        emit(Resource.Success(movies))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

// lead to a Flow exception transparency is violated error
//override suspend fun getPopMovies(page: Int): Flow<Resource<MoviesModel>> = flow {
//
//        try {
//            emit(Resource.Loading())
//            val movies = apiService.getPopMovies(page).toMoviesModel()
//            emit(Resource.Success(movies))
//
//        } catch (e: Exception) {
//            emit(Resource.Error(e.message ?: "An error occurred"))
//        }
//    }

    override suspend fun getTopRatedMovies(page: Int): Flow<Resource<MoviesModel>>  = flow {

        emit(Resource.Loading())
        val movies = apiService.getTopRatedMovies(page).toMoviesModel()
        emit(Resource.Success(movies))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun getUpcomingMovies(page: Int): Flow<Resource<MoviesModel>> = flow {

        emit(Resource.Loading())
        val movies = apiService.getUpcomingMovies(page).toMoviesModel()
        emit(Resource.Success(movies))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }


    override suspend fun getLatestMovie(): Flow<Resource<MovieMainItem>> = flow {
        emit(Resource.Loading())
        val movie = apiService.getLatestMovie().toMovieItem()
        emit(Resource.Success(movie))


    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun searchMovie(query: String, page: Int): Flow<Resource<MoviesModel>> = flow {

        emit(Resource.Loading())
        val movies = apiService.searchMovie(query,page).toMoviesModel()
        emit(Resource.Success(movies))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun getMovieById(movieId: Int): Flow<Resource<MovieDetailModel>> = flow {

        emit(Resource.Loading())
        val movie = apiService.getMovieDetail(movieId).toMovieDetailModel()
        emit(Resource.Success(movie))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun getMovieReviews(movieId: Int): Flow<Resource<MovieReviewModel>> = flow {

        emit(Resource.Loading())
        val movieReviews = apiService.getMovieReviews(movieId).toMovieReviewsModel()
        emit(Resource.Success(movieReviews))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideoModel>>> = flow {

        emit(Resource.Loading())
        val videoList = apiService.getMovieVideos(movieId).results.map { it.toMovieVideoModel() }
        emit(Resource.Success(videoList))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun getMovieCredits(movieId: Int): Flow<Resource<List<MovieCastModel>>>  = flow {

        emit(Resource.Loading())
        val credits = apiService.getMovieCredits(movieId).cast.map { it.toMovieCastModel() }
        emit(Resource.Success(credits))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun getMovieImages(movieId: Int): Flow<Resource<List<MovieImageModel>>> = flow {

        emit(Resource.Loading())
        val images = apiService.getMovieImages(movieId).backdrops.map { it.toMovieImageModel() }
        emit(Resource.Success(images))

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

    }

    override suspend fun getFavoriteMovies(): Flow<Resource<List<MovieMainItem>>> = flow {

        emit(Resource.Loading())

        db.movieDao().getAllMovies()
            .map { movieEntities ->
                movieEntities.map { it.toMovieItem() }
            }.collect{
                movies ->
                emit(Resource.Success(movies))
            }

    }.catch  { e ->

        emit(Resource.Error(e.message ?: "An error occurred"))

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