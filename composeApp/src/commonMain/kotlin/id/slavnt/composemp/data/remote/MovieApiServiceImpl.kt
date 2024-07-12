package id.slavnt.composemp.data.remote

import id.slavnt.composemp.data.remote.dt_object.Movie
import id.slavnt.composemp.data.remote.dt_object.MovieCredits
import id.slavnt.composemp.data.remote.dt_object.MovieDetail
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.MovieVideos
import id.slavnt.composemp.data.remote.dt_object.Movies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.get

class MovieApiServiceImpl(private val client: HttpClient) : MovieApiService {

    override suspend fun getPopMovies(page: Int): Movies =
        client.get("https://api.themoviedb.org/3/movie/popular") {
            parameter("api_key", API_KEY)
            parameter("page", page)
        }.body()

    override suspend fun getTopRatedMovies(page: Int): Movies =
        client.get("https://api.themoviedb.org/3/movie/top_rated") {
            parameter("api_key", API_KEY)
            parameter("page", page)
        }.body()

    override suspend fun getLatestMovie(): Movie =
        client.get("https://api.themoviedb.org/3/movie/latest") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun searchMovie(query: String, page: Int): Movies  =
    client.get("https://api.themoviedb.org/3/search/movie") {
        parameter("api_key", API_KEY)
        parameter("query", query)
        parameter("page", page)
    }.body()

    override suspend fun getMovieDetail(movieId: Int): MovieDetail =
        client.get("https://api.themoviedb.org/3/movie/$movieId") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieReviews(movieId: Int): MovieReviews =
        client.get("https://api.themoviedb.org/3/movie/$movieId/reviews") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieVideos(movieId: Int): MovieVideos =
        client.get("https://api.themoviedb.org/3/movie/$movieId/videos") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieCredits(movieId: Int): MovieCredits =
        client.get("https://api.themoviedb.org/3/movie/$movieId/credits") {
            parameter("api_key", API_KEY)
        }.body()



    companion object{
        const val API_KEY = "ea26e379f31bca18b471e0a75959095c"
        const val BASE_MOVIE_URL = "https://api.themoviedb.org/3/"
    }

}
