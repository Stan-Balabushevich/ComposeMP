package id.slavnt.composemp.domain.models

data class MoviesModel(
    val page: Int = 0,
    val results: List<MovieMainItem> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)
