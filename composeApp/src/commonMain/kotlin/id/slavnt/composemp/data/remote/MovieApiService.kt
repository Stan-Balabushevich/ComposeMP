package id.slavnt.composemp.data.remote

import id.slavnt.composemp.data.remote.dt_object.Movie
import id.slavnt.composemp.data.remote.dt_object.MovieCredits
import id.slavnt.composemp.data.remote.dt_object.MovieDetail
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.MovieVideos
import id.slavnt.composemp.data.remote.dt_object.Movies

interface MovieApiService {

    suspend fun getPopMovies(page: Int): Movies

    suspend fun getTopRatedMovies(page: Int): Movies

    suspend fun getLatestMovie(): Movie

    suspend fun getMovieDetail(movieId: Int): MovieDetail

    suspend fun getMovieReviews(movieId: Int): MovieReviews

    suspend fun  getMovieVideos(movieId: Int): MovieVideos

    suspend fun  getMovieCredits(movieId: Int): MovieCredits

}
