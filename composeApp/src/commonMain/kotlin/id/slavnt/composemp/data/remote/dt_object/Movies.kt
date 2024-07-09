package id.slavnt.composemp.data.remote.dt_object
import id.slavnt.composemp.domain.models.MovieMainItem
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class Movies(
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<Movie> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

@Serializable
data class Movie(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Double = 0.0,
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)


fun Movie.toMovieItem(): MovieMainItem = MovieMainItem(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage
)