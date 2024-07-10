package id.slavnt.composemp.data.remote.dt_object
import id.slavnt.composemp.domain.models.MovieDetailModel
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class MovieDetail(
    @SerialName("adult")
    val adult: Boolean = false,
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null, // This should remain nullable
    @SerialName("budget")
    val budget: Int = 0,
    @SerialName("genres")
    val genres: List<Genre> = emptyList(),
    @SerialName("homepage")
    val homepage: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("imdb_id")
    val imdbId: String = "",
    @SerialName("origin_country")
    val originCountry: List<String> = emptyList(),
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
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany> = emptyList(),
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry> = emptyList(),
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("revenue")
    val revenue: Int = 0,
    @SerialName("runtime")
    val runtime: Int = 0,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    @SerialName("status")
    val status: String = "",
    @SerialName("tagline")
    val tagline: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
    @SerialName("vote_count")
    val voteCount: Int = 0
)

@Serializable
data class BelongsToCollection(
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("poster_path")
    val posterPath: String = ""
)

@Serializable
data class Genre(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("name")
    val name: String = ""
)

@Serializable
data class ProductionCompany(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("logo_path")
    val logoPath: String = "",
    @SerialName("name")
    val name: String = "",
    @SerialName("origin_country")
    val originCountry: String = ""
)

@Serializable
data class ProductionCountry(
    @SerialName("iso_3166_1")
    val iso31661: String = "",
    @SerialName("name")
    val name: String = ""
)

@Serializable
data class SpokenLanguage(
    @SerialName("english_name")
    val englishName: String = "",
    @SerialName("iso_639_1")
    val iso6391: String = "",
    @SerialName("name")
    val name: String = ""
)



fun MovieDetail.toMovieDetailModel(): MovieDetailModel
     = MovieDetailModel(
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    genres = genres,
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies,
    productionCountries = productionCountries,
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages,
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
    )
