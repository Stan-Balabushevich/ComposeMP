package id.slavnt.composemp.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import id.slavnt.composemp.presentation.AppViewModel
import id.slavnt.composemp.presentation.detailscreen.DetailMovieViewModel
import id.slavnt.composemp.presentation.favoritescreen.FavoriteMovieViewModel
import id.slavnt.composemp.presentation.mainscreen.MainScreenViewModel
import org.koin.compose.viewmodel.dsl.viewModel


val presentationModule = module {

//    factory { AppViewModel(db = get(), batteryManager = get()) }

    viewModelOf(::AppViewModel)

//    viewModel {
//        AppViewModel(db = get(), batteryManager = get())
//    }
    viewModelOf(::MainScreenViewModel)


    viewModelOf(::DetailMovieViewModel)

    viewModelOf(::FavoriteMovieViewModel)

//   viewModel {
//       DetailMovieViewModel(get())
//   }

}