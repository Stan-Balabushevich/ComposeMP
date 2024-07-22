package id.slavnt.composemp.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.models.MoviesModel
import id.slavnt.composemp.domain.usecase.AddFavoriteMovieUseCase
import id.slavnt.composemp.domain.usecase.CheckFavoriteMovieUseCase
import id.slavnt.composemp.domain.usecase.GetFavoritesMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetPopMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetTopRatedMoviesUseCase
import id.slavnt.composemp.domain.usecase.GetUpcomingMoviesUseCase
import id.slavnt.composemp.domain.usecase.RemoveFavoriteMovieUseCase
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
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val checkFavoriteMovieUseCase: CheckFavoriteMovieUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
    private val getFavoriteMoviesUseCase: GetFavoritesMoviesUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _popularMovies = MutableStateFlow(MoviesModel())
    val popularMovies: StateFlow<MoviesModel> = _popularMovies

    private val _topRatedMovies = MutableStateFlow(MoviesModel())
    val topRatedMovies: StateFlow<MoviesModel> = _topRatedMovies

    private val _upcomingMovies = MutableStateFlow(MoviesModel())
    val upcomingMovies: StateFlow<MoviesModel> = _upcomingMovies

    private val _searchResult = MutableStateFlow(MoviesModel())
    val searchResult: StateFlow<MoviesModel> = _searchResult

    private val _favoriteMovies = MutableStateFlow<List<MovieMainItem>>(emptyList())
    val favoriteMovies: StateFlow<List<MovieMainItem>> = _favoriteMovies


    init {
        fetchPopularMovies(1)
        fetchTopRatedMovies(1)
        fetchUpcomingMovies(1)
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

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }


    fun toggleFavorite(movie: MovieMainItem) {
        viewModelScope.launch {
            val updatedMovie = movie.copy(favorite = !movie.favorite)
            if (updatedMovie.favorite) {
                addFavoriteMovieUseCase(updatedMovie)
            } else {
                removeFavoriteMovieUseCase(updatedMovie)
            }
            // Update the list in memory
            _popularMovies.value = _popularMovies.value.let { moviesModel ->
                val updatedMovies = moviesModel.results.map {
                    if (it.id == movie.id) updatedMovie else it
                }
                moviesModel.copy(results = updatedMovies)
            }
            _topRatedMovies.value = _topRatedMovies.value.let { moviesModel ->
                val updatedMovies = moviesModel.results.map {
                    if (it.id == movie.id) updatedMovie else it
                }
                moviesModel.copy(results = updatedMovies)
            }
            _searchResult.value = _searchResult.value.let { moviesModel ->
                val updatedMovies = moviesModel.results.map {
                    if (it.id == movie.id) updatedMovie else it
                }
                moviesModel.copy(results = updatedMovies)
            }
            _upcomingMovies.value = _upcomingMovies.value.let { moviesModel ->
                val updatedMovies = moviesModel.results.map {
                    if (it.id == movie.id) updatedMovie else it
                }
                moviesModel.copy(results = updatedMovies)
            }

        }
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
                            result.data?.let {  movieList ->

                                val updatedMovies = movieList.results.map { movie ->
                                    val isFavorite = checkFavoriteMovieUseCase.invoke(movie.id)
                                    movie.copy(favorite = isFavorite)
                                }
                                _searchResult.value = MoviesModel(
                                    results = updatedMovies,
                                    page = movieList.page,
                                    totalPages = movieList.totalPages,
                                    totalResults = movieList.totalResults
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
                        result.data?.let { movieList ->
                            val updatedMovies = movieList.results.map { movie ->
                                val isFavorite = checkFavoriteMovieUseCase.invoke(movie.id)
                                movie.copy(favorite = isFavorite)
                            }
                            _upcomingMovies.value = MoviesModel(
                                results = updatedMovies,
                                page = movieList.page,
                                totalPages = movieList.totalPages,
                                totalResults = movieList.totalResults
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
                        result.data?.let { movieList ->
                            val updatedMovies = movieList.results.map { movie ->
                                val isFavorite = checkFavoriteMovieUseCase.invoke(movie.id)
                                movie.copy(favorite = isFavorite)
                            }
                            _popularMovies.value = MoviesModel(
                                results = updatedMovies,
                                page = movieList.page,
                                totalPages = movieList.totalPages,
                                totalResults = movieList.totalResults
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
                        result.data?.let { movieList ->
                            val updatedMovies = movieList.results.map { movie ->
                                val isFavorite = checkFavoriteMovieUseCase.invoke(movie.id)
                                movie.copy(favorite = isFavorite)
                            }
                           _topRatedMovies.value = MoviesModel(
                                results = updatedMovies,
                                page = movieList.page,
                                totalPages = movieList.totalPages,
                                totalResults = movieList.totalResults
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
