package id.slavnt.composemp.domain.usecase

import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.repository.FakeMoviesRepository
import id.slavnt.composemp.domain.models.MovieMainItem
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class GetFavoritesMoviesUseCaseTest{


    private lateinit var getFavoritesMoviesUseCase: GetFavoritesMoviesUseCase
    private lateinit var repository: FakeMoviesRepository


    @Test
    fun getFavoritesMovies_should_return_list_of_favorite_movies(){

        repository = FakeMoviesRepository()
        getFavoritesMoviesUseCase = GetFavoritesMoviesUseCase(repository)

        val testMovieList =  (1..10).map {
            MovieMainItem(id = 1, title = "Test Movie", voteAverage = 8.5, posterPath = "test_poster.jpg").copy(id = it)
        }

        repository.movies.addAll(testMovieList)



       runTest {
//           val results = getFavoritesMoviesUseCase.invoke().take(2).toList()
           val results = getFavoritesMoviesUseCase.invoke().toList()

           assertTrue(results[0] is Resource.Loading)
           assertTrue(results[1] is Resource.Success)
           assertEquals(results[1].data, testMovieList)

       }

    }

    @Test
    fun getFavoritesMovies_should_return_error_when_repository_returns_error(){

        repository = FakeMoviesRepository()
        repository.shouldReturnError = true
        getFavoritesMoviesUseCase = GetFavoritesMoviesUseCase(repository)

        runTest {
            val results = getFavoritesMoviesUseCase.invoke().toList()

            assertTrue(results[0] is Resource.Loading)
            assertTrue(results[1] is Resource.Error)
            assertEquals(results[1].message, "get fav movies test error")

        }
    }

}