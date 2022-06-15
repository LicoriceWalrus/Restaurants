package com.example.restaurants.features.restaurants.domain.repo

import com.example.restaurants.data.getOrThrow
import com.example.restaurants.features.restaurants.data.api.RestaurantsApi
import com.example.restaurants.features.restaurants.data.dto.RestaurantsDto
import com.example.restaurants.features.restaurants.domain.entity.Restaurant
import com.example.restaurants.features.restaurants.domain.entity.Specialization

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
        name = this.name.orEmpty(),
        logo = this.logo.orEmpty(),
        minCost = this.minCost.orEmpty(),
        deliveryCost = this.deliveryCost.orEmpty(),
        deliveryTime = this.deliveryTime.orEmpty(),
        positiveReviews = this.positiveReviews.orEmpty(),
        reviewsCount = this.reviewsCount.orEmpty(),
        specializations = this.specializations?.map {
            Specialization(it.name.orEmpty())
        } ?: emptyList(),
    )
}