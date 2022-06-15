package com.example.restaurants.features.hits.data.repo

import com.example.restaurants.data.getOrThrow
import com.example.restaurants.features.hits.data.api.HitsApi
import com.example.restaurants.features.hits.data.dto.HitsDto
import com.example.restaurants.features.hits.domain.entity.Hit
import com.example.restaurants.features.hits.domain.repo.HitsRepo

class HitsRepoImpl(
    private val api: HitsApi
) : HitsRepo {

    override suspend fun getHits(): List<Hit> {

        val resp = api.getHits().getOrThrow()

        return resp.map {
            it.mapToEntity()
        }
    }

    private fun HitsDto.mapToEntity() = Hit(
        productName = productName.orEmpty(),
        productImage = productImage.orEmpty(),
        productPrice = productPrice.orEmpty(),
        productDescription = productDescription.orEmpty(),
        restaurantId = restaurantId.orEmpty(),
        restaurantName = restaurantName.orEmpty(),
        restaurantLogo = restaurantLogo.orEmpty(),
    )
}