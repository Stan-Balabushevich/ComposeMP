package id.slavnt.composemp.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.dt_object.Movies
import id.slavnt.composemp.domain.usecase.GetPopMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetTopRatedMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetUpcomingMoviesUseCase
import id.slavnt.composemp.domain.usecase.SearchMovieUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainScreenViewModel(
    private val getPopMoviesUseCase: GetPopMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _popularMovies = MutableStateFlow(Movies())
    val popularMovies: StateFlow<Movies> = _popularMovies

    private val _topRatedMovies = MutableStateFlow(Movies())
    val topRatedMovies: StateFlow<Movies> = _topRatedMovies

    private val _upcomingMovies = MutableStateFlow(Movies())
    val upcomingMovies: StateFlow<Movies> = _upcomingMovies

    private val _searchResult = MutableStateFlow(Movies())
    val searchResult: StateFlow<Movies> = _searchResult

    init {
        fetchPopularMovies(1)
        fetchTopRatedMovies(1)
        fetchUpcomingMovies(1)
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private var searchJob: Job? = null

    fun onSearchQueryChanged(query: String, page: Int) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)  // Debounce time
            if (query.isNotBlank()) {

                searchMovieUseCase.invoke(query, page).collect{ result ->
                    when(result){
                        is Resource.Error -> {
                            println("Error: ${result.message}")
                        }
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            result.data?.let {
                                _searchResult.value = Movies(
                                    results = it.results,
                                    page = it.page,
                                    totalPages = it.totalPages,
                                    totalResults = it.totalResults
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun fetchUpcomingMovies(page: Int) {
        viewModelScope.launch {
            getUpcomingMoviesUseCase.invoke(page).collect { result ->
                when(result){
                    is Resource.Error -> {
                        println("Error: ${result.message}")
                    }
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        result.data?.let {
                            _upcomingMovies.value = Movies(
                                results = it.results,
                                page = it.page,
                                totalPages = it.totalPages,
                                totalResults = it.totalResults
                            )
                        }
                    }
                }
            }
        }
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
                }
            }
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
    fun loadNextUpcomingPage() {
        val currentPage = _upcomingMovies.value.page
        if (currentPage < _upcomingMovies.value.totalPages) {
            fetchUpcomingMovies(currentPage + 1)
        }
    }

    fun loadPreviousUpcomingPage() {
        val currentPage = _upcomingMovies.value.page
        if (currentPage > 1) {
            fetchUpcomingMovies(currentPage - 1)
        }
    }

    fun loadNextSearchPage(query: String) {
        val currentPage = _searchResult.value.page
        if (currentPage < _searchResult.value.totalPages) {
            onSearchQueryChanged(query ,currentPage + 1)
        }
    }

    fun loadPreviousSearchPage(query: String) {
        val currentPage = _searchResult.value.page
        if (currentPage > 1) {
            onSearchQueryChanged(query ,currentPage - 1)
        }
    }



}
