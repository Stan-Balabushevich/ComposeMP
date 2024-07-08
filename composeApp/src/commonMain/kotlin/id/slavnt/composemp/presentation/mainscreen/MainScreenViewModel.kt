package id.slavnt.composemp.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.slavnt.composemp.common.Resource
import id.slavnt.composemp.domain.models.MovieMainItem
import id.slavnt.composemp.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(private val getMoviesUseCase: GetMoviesUseCase): ViewModel() {


    private val _popularMovies = MutableStateFlow<List<MovieMainItem>>(emptyList())
    val popularMovies: StateFlow<List<MovieMainItem>> = _popularMovies

    private val _topRatedMovies = MutableStateFlow<List<MovieMainItem>>(emptyList())
    val topRatedMovies: StateFlow<List<MovieMainItem>> = _topRatedMovies


    init {

        getMovies()


    }

    private fun getMovies() {
        viewModelScope.launch{
             getMoviesUseCase.invoke().collect { result ->
                 when(result){
                     is Resource.Error -> {}
                     is Resource.Loading -> {}
                     is Resource.Success -> {
                         result.data?.let { movies ->
                             _popularMovies.value = movies
                         }
                     }
                 }
             }
        }
    }



}