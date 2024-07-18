package id.slavnt.composemp.data.local.database.db_object

import androidx.room.Entity
import androidx.room.PrimaryKey
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieMainItem


@Entity(tableName = "movie_table")
data class MovieEntity (
    @PrimaryKey (autoGenerate = false) val id: Int,
    val originalTitle: String,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val favorite: Boolean = false
)

fun MovieMainItem.toMovieEntity() = MovieEntity(
    id = id,
    originalTitle = "originalTitle",
    posterPath = posterPath,
    title = title,
    voteAverage = voteAverage,
    favorite = favorite
    )

fun MovieEntity.toMovieItem(): MovieMainItem =
    MovieMainItem(
        id = id,
        title = title,
        posterPath = posterPath,
        voteAverage = voteAverage,
        favorite = favorite)

fun MovieDetailModel.toMovieEntity() =
    MovieEntity(
        id = id,
        originalTitle = originalTitle,
        posterPath = posterPath,
        title = title,
        voteAverage = voteAverage,
        favorite = favorite
    )