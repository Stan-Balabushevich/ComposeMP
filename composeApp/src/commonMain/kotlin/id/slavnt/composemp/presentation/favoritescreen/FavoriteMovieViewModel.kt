package id.slavnt.composemp.presentation.favoritescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.usecase.GetFavoritesMoviesUseCase
import id.slavnt.composemp.domain.usecase.RemoveFavoriteMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteMovieViewModel(
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
    private val getFavoriteMoviesUseCase: GetFavoritesMoviesUseCase): ViewModel() {



    private val _favoriteMovies = MutableStateFlow<List<MovieMainItem>>(emptyList())
    val favoriteMovies: StateFlow<List<MovieMainItem>> = _favoriteMovies


    init {

        fetchFavoriteMovies()

    }

    private fun fetchFavoriteMovies() {
        viewModelScope.launch {
            getFavoriteMoviesUseCase.invoke().collect { result ->
                when(result){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        result.data?.let {
                            _favoriteMovies.value = it
                        }
                    }
                }}
        }
    }

    fun removeFavoriteMovie(movie: MovieMainItem) {
        viewModelScope.launch {
            removeFavoriteMovieUseCase(movie)
            // Update the list in memory
            _favoriteMovies.value = _favoriteMovies.value.filter { it.id != movie.id }
        }
    }


}