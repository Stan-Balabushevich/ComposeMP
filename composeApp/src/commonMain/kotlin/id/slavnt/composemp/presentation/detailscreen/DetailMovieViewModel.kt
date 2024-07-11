package id.slavnt.composemp.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.data.remote.dt_object.MovieReviews
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.usecase.GetMovieByIdUseCase
import id.slavnt.composemp.domain.usecase.GetMovieReviewsUseCase
import id.slavnt.composemp.domain.usecase.GetMovieVideosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getMovieVideosUseCase: GetMovieVideosUseCase
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetail: StateFlow<MovieDetailModel?> = _movieDetail

    private val _movieReviews = MutableStateFlow<MovieReviews?>(null)
    val movieReviews: StateFlow<MovieReviews?> = _movieReviews

    fun setMovieId(movieId: Int) {
        loadMovieDetails(movieId)
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

}