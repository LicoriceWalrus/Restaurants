package com.example.restaurants.features.restaurants

import com.example.restaurants.features.restaurants.data.api.RestaurantsApi
import com.example.restaurants.features.restaurants.domain.interactor.RestaurantsInteractor
import com.example.restaurants.features.restaurants.domain.interactor.RestaurantsInteractorImpl
import com.example.restaurants.features.restaurants.domain.repo.RestaurantsRepo
import com.example.restaurants.features.restaurants.domain.repo.RestaurantsRepoImpl
import com.example.restaurants.features.restaurants.presentation.RestaurantsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val restaurantsModule = module {

    single<RestaurantsApi> {
        get<Retrofit>()
            .create(RestaurantsApi::class.java)
    }

    factory<RestaurantsRepo> {
        RestaurantsRepoImpl(get())
    }

    factory<RestaurantsInteractor> {
        RestaurantsInteractorImpl(get())
    }

    viewModel { RestaurantsViewModel(get()) }

}