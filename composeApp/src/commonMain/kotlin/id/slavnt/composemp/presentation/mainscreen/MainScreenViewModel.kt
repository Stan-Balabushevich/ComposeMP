package id.slavnt.composemp.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.domain.usecase.GetPopMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetTopRatedMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val getPopMoviesUseCase: GetPopMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _popularMovies = MutableStateFlow(Movies())
    val popularMovies: StateFlow<Movies> = _popularMovies

    private val _topRatedMovies = MutableStateFlow(Movies())
    val topRatedMovies: StateFlow<Movies> = _topRatedMovies

    init {
        fetchPopularMovies(1)
        fetchTopRatedMovies(1)
    }

    private fun fetchPopularMovies(page: Int) {
        viewModelScope.launch {
            getPopMoviesUseCase.invoke(page).collect { result ->
                when(result){
                    is Resource.Error -> {
                        println("Error: ${result.message}")
                    }
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        result.data?.let {
                            _popularMovies.value = Movies(
                                results = it.results,
                                page = it.page,
                                totalPages = it.totalPages,
                                totalResults = it.totalResults
                            )
                        }
                    }
                }}
        }
    }

    private fun fetchTopRatedMovies(page: Int) {
        viewModelScope.launch {
            getTopRatedMoviesUseCase.invoke(page).collect { result ->
                when(result){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        result.data?.let {
                           _topRatedMovies.value = Movies(
                                results = it.results,
                                page = it.page,
                                totalPages = it.totalPages,
                                totalResults = it.totalResults
                            )
                        }
                    }
                }}
        }
    }

    fun loadNextPopularPage() {
        val currentPage = _popularMovies.value.page
        if (currentPage < _popularMovies.value.totalPages) {
            fetchPopularMovies(currentPage + 1)
        }
    }

    fun loadPreviousPopularPage() {
        val currentPage = _popularMovies.value.page
        if (currentPage > 1) {
            fetchPopularMovies(currentPage - 1)
        }
    }

    fun loadNextTopRatedPage() {
        val currentPage = _topRatedMovies.value.page
        if (currentPage < _topRatedMovies.value.totalPages) {
            fetchTopRatedMovies(currentPage + 1)
        }
    }

    fun loadPreviousTopRatedPage() {
        val currentPage = _topRatedMovies.value.page
        if (currentPage > 1) {
            fetchTopRatedMovies(currentPage - 1)
        }
    }
}
