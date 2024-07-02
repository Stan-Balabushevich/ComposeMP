package id.slavnt.composemp.di

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import presentation.AppViewModel

val presentationModule = module {

//    factory { AppViewModel(db = get(), batteryManager = get()) }

    viewModelOf(::AppViewModel)

//    viewModel {
//        AppViewModel(db = get(), batteryManager = get())
//    }

}