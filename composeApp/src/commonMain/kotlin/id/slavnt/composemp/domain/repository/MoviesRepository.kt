package id.slavnt.composemp.domain.repository

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.domain.models.MovieMainItem
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getMovies(): Flow<Resource<List<MovieMainItem>>>

}