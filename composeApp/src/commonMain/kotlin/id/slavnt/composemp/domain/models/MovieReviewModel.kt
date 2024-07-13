package id.slavnt.composemp.domain.models

data class MovieReviewModel(
    val id: Int,
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val reviews: List<ReviewModel>
)

data class ReviewModel(
    val author: String,
    val authorDetails: AuthorDetailsModel,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)

data class AuthorDetailsModel(
    val avatarPath: String,
    val name: String,
    val rating: Double?,
    val username: String
)
