package com.example.restaurants.features.reviews

import com.example.restaurants.features.reviews.data.api.ReviewsApi
import com.example.restaurants.features.reviews.domain.interactor.ReviewsInteractor
import com.example.restaurants.features.reviews.domain.interactor.ReviewsInteractorImpl
import com.example.restaurants.features.reviews.domain.repo.ReviewsRepo
import com.example.restaurants.features.reviews.domain.repo.ReviewsRepoImpl
import com.example.restaurants.features.reviews.presentation.ReviewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val reviewModel = module {

    single<ReviewsApi> {
        get<Retrofit>()
            .create(ReviewsApi::class.java)
    }

    factory<ReviewsRepo> {
        ReviewsRepoImpl(get())
    }

    factory<ReviewsInteractor> {
        ReviewsInteractorImpl(get())
    }

    viewModel { ReviewsViewModel(get()) }
}