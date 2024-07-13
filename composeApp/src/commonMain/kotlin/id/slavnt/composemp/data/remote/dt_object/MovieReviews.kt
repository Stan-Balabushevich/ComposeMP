package id.slavnt.composemp.data.remote.dt_object
import id.slavnt.composemp.domain.models.AuthorDetailsModel
import id.slavnt.composemp.domain.models.MovieReviewModel
import id.slavnt.composemp.domain.models.ReviewModel
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Serializable
data class MovieReviews(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val results: List<Result> = emptyList(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)

@Serializable
data class Result(
    @SerialName("author")
    val author: String = "",
    @SerialName("author_details")
    val authorDetails: AuthorDetails = AuthorDetails(),
    @SerialName("content")
    val content: String = "",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("updated_at")
    val updatedAt: String = "",
    @SerialName("url")
    val url: String = ""
)

@Serializable
data class AuthorDetails(
    @SerialName("avatar_path")
    val avatarPath: String? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("username")
    val username: String = ""
)


fun MovieReviews.toMovieReviewsModel(): MovieReviewModel =
    MovieReviewModel(
        id = id,
        page = page,
        totalPages =totalPages,
        totalResults = totalResults,
        reviews = results.map { review ->
            ReviewModel(
                author = review.author,
                authorDetails = AuthorDetailsModel(
                    avatarPath = review.authorDetails.avatarPath ?: "",
                    name = review.authorDetails.name,
                    rating = review.authorDetails.rating.takeIf { it != -1.0 },
                    username = review.authorDetails.username
                ),
                content = review.content,
                createdAt = review.createdAt,
                id = review.id,
                updatedAt = review.updatedAt,
                url = review.url
            )
        }
    )