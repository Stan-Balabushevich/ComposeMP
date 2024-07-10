package id.slavnt.composemp.domain.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieMainItem
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopMovies(page: Int): Flow<Resource<Movies>>

    suspend fun getTopRatedMovies(page: Int): Flow<Resource<Movies>>

    suspend fun getMovieById(movieId: Int): Flow<Resource<MovieDetailModel>>


}