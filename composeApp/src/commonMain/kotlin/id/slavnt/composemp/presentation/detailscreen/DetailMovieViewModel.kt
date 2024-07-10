package id.slavnt.composemp.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.domain.models.MovieDetailModel
import id.slavnt.composemp.domain.usecase.GetMovieByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val getMovieByIdUseCase: GetMovieByIdUseCase) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailModel?>(null)
    val movieDetail: StateFlow<MovieDetailModel?> = _movieDetail

    fun setMovieId(movieId: Int) {
        // Load the movie details using the movieId
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

}