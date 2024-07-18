package id.slavnt.composemp.domain.models

data class MovieDetailModel(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<String>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<String>,
    val productionCountries: List<String>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<String>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val favorite: Boolean = false
)

