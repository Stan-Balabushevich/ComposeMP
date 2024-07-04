package id.slavnt.composemp.data.remote

import id.slavnt.composemp.data.remote.dt_object.MovieDetail
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.MoviesPop

interface MovieApiService {

    suspend fun getPopMovies(): MoviesPop

    suspend fun getMovieDetail(movieId: Int): MovieDetail

    suspend fun getMovieReviews(movieId: Int): MovieReviews

    suspend fun  getMovieVideos(movieId: Int): MovieReviews

}
