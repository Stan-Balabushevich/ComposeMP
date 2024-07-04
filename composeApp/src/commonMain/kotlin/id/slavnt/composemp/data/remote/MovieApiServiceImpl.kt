package id.slavnt.composemp.data.remote

import id.slavnt.composemp.data.remote.dt_object.MovieDetail
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.MoviesPop
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.get

class MovieApiServiceImpl(private val client: HttpClient) : MovieApiService {

    override suspend fun getPopMovies(): MoviesPop =
        client.get("https://api.themoviedb.org/3/movie/popular") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieDetail(movieId: Int): MovieDetail =
        client.get("https://api.themoviedb.org/3/movie/$movieId") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieReviews(movieId: Int): MovieReviews =
        client.get("https://api.themoviedb.org/3/movie/$movieId/reviews") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieVideos(movieId: Int): MovieReviews =
        client.get("https://api.themoviedb.org/3/movie/$movieId/videos") {
            parameter("api_key", API_KEY)
        }.body()


    companion object{
        const val API_KEY = "ea26e379f31bca18b471e0a75959095c"
    }

}
