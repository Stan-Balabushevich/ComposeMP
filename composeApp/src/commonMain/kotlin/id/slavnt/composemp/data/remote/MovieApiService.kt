package id.slavnt.composemp.data.remote

import id.slavnt.composemp.data.remote.dt_object.MovieDetail
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.MovieVideos
import id.slavnt.composemp.data.remote.dt_object.Movies

interface MovieApiService {

    suspend fun getPopMovies(page: Int): Movies

    suspend fun getTopRatedMovies(page: Int): Movies

    suspend fun getMovieDetail(movieId: Int): MovieDetail

    suspend fun getMovieReviews(movieId: Int): MovieReviews

    suspend fun  getMovieVideos(movieId: Int): MovieVideos

}
