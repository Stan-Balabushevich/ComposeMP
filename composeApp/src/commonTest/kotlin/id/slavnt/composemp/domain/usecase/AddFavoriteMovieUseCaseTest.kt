package id.slavnt.composemp.domain.usecase

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import id.slavnt.composemp.data.repository.FakeMoviesRepository
import id.slavnt.composemp.domain.models.MovieMainItem
import kotlinx.coroutines.runBlocking
import kotlin.test.Test


class AddFavoriteMovieUseCaseTest{

    private lateinit var addFavoriteMovieUseCase: AddFavoriteMovieUseCase
    private lateinit var repository: FakeMoviesRepository


    @Test
    fun addFavoriteMovie_should_add_movie_to_favorites(){

        repository = FakeMoviesRepository()
        addFavoriteMovieUseCase = AddFavoriteMovieUseCase(repository)

        val testMovie = MovieMainItem(
            id = 1,
            title = "Test Movie",
            voteAverage = 8.5,
            posterPath = "test_poster.jpg"
        )

        runBlocking {
            addFavoriteMovieUseCase.invoke(testMovie)
        }

        val addedMovie = repository.movies.find { it.id == testMovie.id }

//        assertThat(repository.movies).contains(addedMovie)
        assertThat(addedMovie).isNotNull()
        assertThat(addedMovie?.favorite).isEqualTo(true)

    }

}