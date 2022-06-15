package com.example.restaurants.features.hits

import com.example.restaurants.features.hits.data.api.HitsApi
import com.example.restaurants.features.hits.data.repo.HitsRepoImpl
import com.example.restaurants.features.hits.domain.interactor.HitsInteractor
import com.example.restaurants.features.hits.domain.interactor.HitsInteractorImpl
import com.example.restaurants.features.hits.domain.repo.HitsRepo
import com.example.restaurants.features.hits.presentation.HitsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val hitsModule = module {

    single<HitsApi> {
        get<Retrofit>()
            .create(HitsApi::class.java)
    }

    factory<HitsRepo> {
        HitsRepoImpl(get())
    }

    factory<HitsInteractor> {
        HitsInteractorImpl(get())
    }

    viewModel { HitsViewModel(get()) }
}