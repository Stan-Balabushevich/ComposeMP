package id.slavnt.composemp.di

import org.koin.dsl.module
import presentation.AppViewModel

val presentationModule = module {

    factory { AppViewModel(db = get(), batteryManager = get()) }

}