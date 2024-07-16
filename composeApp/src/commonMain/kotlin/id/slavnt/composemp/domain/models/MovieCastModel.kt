package id.slavnt.composemp.domain.models

data class MovieCastModel (
    val castId: Int,
    val character: String,
    val creditId: String,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String
)