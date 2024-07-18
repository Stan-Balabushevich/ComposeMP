package id.slavnt.composemp.domain.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.dt_object.MovieCredits
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.domain.models.MovieCastModel
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieImageModel
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.models.MovieReviewModel
import id.slavnt.composemp.domain.models.MovieVideoModel
import id.slavnt.composemp.domain.models.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopMovies(page: Int): Flow<Resource<MoviesModel>>

    suspend fun getTopRatedMovies(page: Int): Flow<Resource<MoviesModel>>

    suspend fun getUpcomingMovies(page: Int): Flow<Resource<MoviesModel>>

    suspend fun getLatestMovie(): Flow<Resource<MovieMainItem>>

    suspend fun searchMovie(query: String, page: Int): Flow<Resource<MoviesModel>>

    suspend fun getMovieById(movieId: Int): Flow<Resource<MovieDetailModel>>

    suspend fun getMovieReviews(movieId: Int): Flow<Resource<MovieReviewModel>>

    suspend fun getMovieVideos(movieId: Int): Flow<Resource<List<MovieVideoModel>>>

    suspend fun getMovieCredits(movieId: Int): Flow<Resource<List<MovieCastModel>>>

    suspend fun getMovieImages(movieId: Int): Flow<Resource<List<MovieImageModel>>>

    suspend fun getFavoriteMovies():  Flow<Resource<List<MovieMainItem>>>

    suspend fun addFavoriteMovie(movie: MovieMainItem)

    suspend fun removeFavoriteMovie(movie: MovieMainItem)

    suspend fun checkFavoriteMovie(movieId: Int): Boolean


}