package id.slavnt.composemp.di

import id.slavnt.composemp.domain.usecase.GetMoviesUseCase
import org.koin.dsl.module

val domainModule = module {


    factory {
        GetMoviesUseCase(repository = get())
    }


}