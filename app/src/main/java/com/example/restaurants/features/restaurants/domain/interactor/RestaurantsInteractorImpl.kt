package com.example.restaurants.features.restaurants.domain.interactor

import com.example.restaurants.features.restaurants.domain.entity.Restaurant
import com.example.restaurants.features.restaurants.domain.repo.RestaurantsRepo

class RestaurantsInteractorImpl(
    private val repo: RestaurantsRepo
) : RestaurantsInteractor {

    override suspend fun getRestaurants(): List<Restaurant> = repo.getRestaurants()
}