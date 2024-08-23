package id.slavnt.composemp.data.remote

import id.slavnt.composemp.data.remote.dt_object.Movie
import id.slavnt.composemp.data.remote.dt_object.MovieCredits
import id.slavnt.composemp.data.remote.dt_object.MovieDetail
import id.slavnt.composemp.data.remote.dt_object.MovieImages
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.data.remote.dt_object.MovieVideos
import id.slavnt.composemp.data.remote.dt_object.Movies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.get

//  If changes are needed to be made in this file:
//  - check git ignore and remove the file
//  - set const val API_KEY = "your_api_key_here" before pushing
class MovieApiServiceImpl(private val client: HttpClient) : MovieApiService {

    override suspend fun getPopMovies(page: Int): Movies =
        client.get("$BASE_MOVIE_URL/movie/popular") {
            parameter("api_key", API_KEY)
            parameter("page", page)
        }.body()

    override suspend fun getTopRatedMovies(page: Int): Movies =
        client.get("$BASE_MOVIE_URL/movie/top_rated") {
            parameter("api_key", API_KEY)
            parameter("page", page)
        }.body()

    override suspend fun getUpcomingMovies(page: Int): Movies  =
        client.get("$BASE_MOVIE_URL/movie/upcoming") {
            parameter("api_key", API_KEY)
            parameter("page", page)
        }.body()

    override suspend fun getLatestMovie(): Movie =
        client.get("$BASE_MOVIE_URL/movie/latest") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun searchMovie(query: String, page: Int): Movies  =
    client.get("$BASE_MOVIE_URL/search/movie") {
        parameter("api_key", API_KEY)
        parameter("query", query)
        parameter("page", page)
    }.body()

    override suspend fun getMovieDetail(movieId: Int): MovieDetail =
        client.get("$BASE_MOVIE_URL/movie/$movieId") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieReviews(movieId: Int): MovieReviews =
        client.get("$BASE_MOVIE_URL/movie/$movieId/reviews") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieVideos(movieId: Int): MovieVideos =
        client.get("$BASE_MOVIE_URL/movie/$movieId/videos") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieCredits(movieId: Int): MovieCredits =
        client.get("$BASE_MOVIE_URL/movie/$movieId/credits") {
            parameter("api_key", API_KEY)
        }.body()

    override suspend fun getMovieImages(movieId: Int): MovieImages =
        client.get("$BASE_MOVIE_URL/movie/$movieId/images") {
            parameter("api_key", API_KEY)
        }.body()

    companion object{
        const val API_KEY = "your_api_key_here"
        const val BASE_MOVIE_URL = "https://api.themoviedb.org/3"
    }

}
