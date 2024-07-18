package id.slavnt.composemp.domain.models

data class MovieMainItem (
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val favorite: Boolean = false
)