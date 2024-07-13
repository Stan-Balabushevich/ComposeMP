package id.slavnt.composemp.domain.models

data class MovieImageModel (
    val filePath: String,
    val width: Int,
    val height: Int,
    val aspectRatio: Double,
)