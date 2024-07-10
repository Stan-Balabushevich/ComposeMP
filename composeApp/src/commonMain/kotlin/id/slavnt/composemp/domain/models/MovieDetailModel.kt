package id.slavnt.composemp.domain.models

import id.slavnt.composemp.data.remote.dt_object.Genre
import id.slavnt.composemp.data.remote.dt_object.ProductionCompany
import id.slavnt.composemp.data.remote.dt_object.ProductionCountry
import id.slavnt.composemp.data.remote.dt_object.SpokenLanguage

data class MovieDetailModel(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<Genre> = emptyList(),
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originCountry: List<String> = emptyList(),
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
