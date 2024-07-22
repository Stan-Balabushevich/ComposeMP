package id.slavnt.composemp.di

import id.slavnt.composemp.presentation.detailscreen.DetailMovieViewModel
import id.slavnt.composemp.presentation.mainscreen.MainScreenViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {

    viewModelOf(::MainScreenViewModel)

    viewModelOf(::DetailMovieViewModel)

//   viewModel {
//       DetailMovieViewModel(get())
//   }

}