package id.slavnt.composemp.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import id.slavnt.composemp.presentation.AppViewModel
import id.slavnt.composemp.presentation.mainscreen.MainScreenViewModel


val presentationModule = module {

//    factory { AppViewModel(db = get(), batteryManager = get()) }

    viewModelOf(::AppViewModel)

//    viewModel {
//        AppViewModel(db = get(), batteryManager = get())
//    }
    viewModelOf(::MainScreenViewModel)

//   viewModel {
//       MainScreenViewModel(getMoviesUseCase = get())
//   }

}