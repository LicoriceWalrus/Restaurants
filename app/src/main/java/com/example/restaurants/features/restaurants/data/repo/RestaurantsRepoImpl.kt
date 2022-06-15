package com.example.restaurants.features.restaurants.data.repo

import com.example.restaurants.data.getOrThrow
import com.example.restaurants.features.restaurants.data.api.RestaurantsApi
import com.example.restaurants.features.restaurants.data.dto.RestaurantsDto
import com.example.restaurants.features.restaurants.domain.entity.Restaurant
import com.example.restaurants.features.restaurants.domain.entity.Specialization
import com.example.restaurants.features.restaurants.domain.repo.RestaurantsRepo

class RestaurantsRepoImpl(
    private val api: RestaurantsApi
) : RestaurantsRepo {

    override suspend fun getRestaurants(): List<Restaurant> {

        val resp = api.getRestaurants().getOrThrow()
        return resp.map {
            it.mapToEntity()
        }
    }

    private fun RestaurantsDto.mapToEntity() = Restaurant(
        name = name.orEmpty(),
        logo = logo.orEmpty(),
        minCost = minCost.orEmpty(),
        deliveryCost = deliveryCost.orEmpty(),
        deliveryTime = deliveryTime.orEmpty(),
        positiveReviews = positiveReviews.orEmpty(),
        reviewsCount = reviewsCount.orEmpty(),
        specializations = specializations?.map {
            Specialization(it.name.orEmpty())
        } ?: emptyList(),
    )
}