package id.slavnt.composemp.data.remote.dt_object
import id.slavnt.composemp.domain.models.MovieImageModel
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class MovieImages(
    @SerialName("backdrops")
    val backdrops: List<Backdrop> = listOf(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("logos")
    val logos: List<Logo> = listOf(),
    @SerialName("posters")
    val posters: List<Poster> = listOf()
)

@Serializable
data class Backdrop(
    @SerialName("aspect_ratio")
    val aspectRatio: Double = 0.0,
    @SerialName("file_path")
    val filePath: String = "",
    @SerialName("height")
    val height: Int = 0,
    @SerialName("iso_639_1")
    val iso6391: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("width")
    val width: Int = 0
)

@Serializable
data class Logo(
    @SerialName("aspect_ratio")
    val aspectRatio: Double = 0.0,
    @SerialName("file_path")
    val filePath: String = "",
    @SerialName("height")
    val height: Int = 0,
    @SerialName("iso_639_1")
    val iso6391: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("width")
    val width: Int = 0
)

@Serializable
data class Poster(
    @SerialName("aspect_ratio")
    val aspectRatio: Double = 0.0,
    @SerialName("file_path")
    val filePath: String = "",
    @SerialName("height")
    val height: Int = 0,
    @SerialName("iso_639_1")
    val iso6391: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("width")
    val width: Int = 0
)

fun Backdrop.toMovieImageModel(): MovieImageModel =
    MovieImageModel(
        filePath = filePath,
        width = width,
        height = height,
        aspectRatio = aspectRatio
    )

fun Poster.toMovieImageModel(): MovieImageModel =
    MovieImageModel(
        filePath = filePath,
        width = width,
        height = height,
        aspectRatio = aspectRatio
    )