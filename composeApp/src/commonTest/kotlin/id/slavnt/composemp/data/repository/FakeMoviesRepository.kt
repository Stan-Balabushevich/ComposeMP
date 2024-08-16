package id.slavnt.composemp.data.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.domain.models.MovieCastModel
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieImageModel
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.models.MovieReviewModel
import id.slavnt.composemp.domain.models.MovieVideoModel
import id.slavnt.composemp.domain.models.MoviesModel
import id.slavnt.composemp.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMoviesRepository: MoviesRepository {

    val movies = mutableListOf<MovieMainItem>()
    var shouldReturnError = false

    override suspend fun getPopMovies(page: Int): Flow<Resource<MoviesModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(page: Int): Flow<Resource<MoviesModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcomingMovies(page: Int): Flow<Resource<MoviesModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getLatestMovie(): Flow<Resource<MovieMainItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovie(query: String, page: Int): Flow<Resource<MoviesModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieById(movieId: Int): Flow<Resource<MovieDetailModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieReviews(movieId: Int): Flow<Resource<MovieReviewModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideoModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieCredits(movieId: Int): Flow<Resource<List<MovieCastModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieImages(movieId: Int): Flow<Resource<List<MovieImageModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteMovies(): Flow<Resource<List<MovieMainItem>>>  = flow {
        emit(Resource.Loading())

        delay(200)

        if (shouldReturnError) {
            emit(Resource.Error("get fav movies test error"))
        } else {
            emit(Resource.Success(movies))
        }
    }

    override suspend fun addFavoriteMovie(movie: MovieMainItem) {
       movies.add(movie)
    }

    override suspend fun removeFavoriteMovie(movie: MovieMainItem) {
        TODO("Not yet implemented")
    }

    override suspend fun checkFavoriteMovie(movieId: Int): Boolean {
        TODO("Not yet implemented")
    }
}