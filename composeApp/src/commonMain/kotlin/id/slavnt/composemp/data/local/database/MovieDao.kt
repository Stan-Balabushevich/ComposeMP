package id.slavnt.composemp.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import id.slavnt.composemp.data.local.database.db_object.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Upsert
    suspend fun upsert(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_table WHERE id = :movieId LIMIT 1")
    suspend fun getMovieById(movieId: Int): MovieEntity?

}