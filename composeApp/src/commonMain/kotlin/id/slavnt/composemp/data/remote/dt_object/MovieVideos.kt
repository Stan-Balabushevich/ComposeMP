package id.slavnt.composemp.data.remote.dt_object
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class MovieVideos(
    @SerialName("id")
    val id: Int,
    @SerialName("results")
    val results: List<Video>
)

@Serializable
data class Video(
    @SerialName("id")
    val id: String,
    @SerialName("iso_3166_1")
    val iso31661: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    @SerialName("key")
    val key: String,
    @SerialName("name")
    val name: String,
    @SerialName("official")
    val official: Boolean,
    @SerialName("published_at")
    val publishedAt: String,
    @SerialName("site")
    val site: String,
    @SerialName("size")
    val size: Int,
    @SerialName("type")
    val type: String
)