package id.slavnt.composemp.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.dt_object.MovieCredits
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.domain.models.MovieCastModel
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.models.MovieImageModel
import id.slavnt.composemp.domain.models.MovieReviewModel
import id.slavnt.composemp.domain.models.MovieVideoModel
import id.slavnt.composemp.domain.usecase.AddFavoriteMovieUseCase
import id.slavnt.composemp.domain.usecase.CheckFavoriteMovieUseCase
import id.slavnt.composemp.domain.usecase.GetMovieByIdUseCase
import id.slavnt.composemp.domain.usecase.GetMovieCreditsUseCase
import id.slavnt.composemp.domain.usecase.GetMovieImagesUseCase
import id.slavnt.composemp.domain.usecase.GetMovieReviewsUseCase
import id.slavnt.composemp.domain.usecase.GetMovieVideosUseCase
import id.slavnt.composemp.domain.usecase.RemoveFavoriteMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getMovieVideosUseCase: GetMovieVideosUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val checkFavoriteMovieUseCase: CheckFavoriteMovieUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetail: StateFlow<MovieDetailModel?> = _movieDetail

    private val _movieReviews = MutableStateFlow<MovieReviewModel?>(null)
    val movieReviews: StateFlow<MovieReviewModel?> = _movieReviews

    private val _movieVideos = MutableStateFlow<List<MovieVideoModel>>(emptyList())
    val movieVideos: StateFlow<List<MovieVideoModel>> = _movieVideos

    private val _movieCredits = MutableStateFlow<List<MovieCastModel>>(emptyList())
    val movieCredits: StateFlow<List<MovieCastModel>> = _movieCredits

    private val _movieImages = MutableStateFlow<List<MovieImageModel>>(emptyList())
    val movieImages: StateFlow<List<MovieImageModel>> = _movieImages


    fun setMovieId(movieId: Int) {
        loadMovieDetails(movieId)
        loadMovieReviews(movieId)
        loadMovieVideos(movieId)
        loadMovieCredits(movieId)
        loadMovieImages(movieId)
    }

    private fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieByIdUseCase.invoke(movieId).collect { result ->
                when(result){
                    is Resource.Error -> {
                        println("Error: ${result.message}")
                    }
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        result.data?.let { movieDetail ->
                            _movieDetail.value = movieDetail
                        }
                    }
                }}
        }
    }

    private fun loadMovieReviews(movieId: Int) {
            viewModelScope.launch {
                getMovieReviewsUseCase.invoke(movieId).collect { result ->
                    when(result){
                        is Resource.Error -> {
                            println("Error: ${result.message}")
                        }
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            result.data?.let { movieReviews ->
                                _movieReviews.value = movieReviews
                            }
                        }
                    }}
            }
        }

    private fun loadMovieCredits(movieId: Int) {
                viewModelScope.launch {
                    getMovieCreditsUseCase.invoke(movieId).collect { result ->
                        when(result){
                            is Resource.Error -> {
                                println("Error: ${result.message}")
                            }
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                result.data?.let { movieCredits ->
                                    _movieCredits.value = movieCredits
                                }
                            }
                        }}
                }
            }

    private fun loadMovieVideos(movieId: Int) {
                    viewModelScope.launch {
                        getMovieVideosUseCase.invoke(movieId).collect { result ->
                            when(result){
                                is Resource.Error -> {
                                    println("Error: ${result.message}")
                                }
                                is Resource.Loading -> {}
                                is Resource.Success -> {
                                    result.data?.let { movieVideos ->
                                        _movieVideos.value = movieVideos
                                    }
                                }
                            }}
                    }
                }

    private fun loadMovieImages(movieId: Int) {
                        viewModelScope.launch {
                            getMovieImagesUseCase.invoke(movieId).collect { result ->
                                when(result){
                                    is Resource.Error -> {
                                        println("Error: ${result.message}")
                                    }
                                    is Resource.Loading -> {}
                                    is Resource.Success -> {
                                        result.data?.let { movieImages ->
                                            _movieImages.value = movieImages
                                        }
                                    }
                                }}
                        }
                    }

}