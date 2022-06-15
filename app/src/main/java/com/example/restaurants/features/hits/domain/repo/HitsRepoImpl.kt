package com.example.restaurants.features.hits.domain.repo

import com.example.restaurants.data.getOrThrow
import com.example.restaurants.features.hits.data.api.HitsApi
import com.example.restaurants.features.hits.data.dto.HitsDto
import com.example.restaurants.features.hits.domain.entity.Hit

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
        productName = this.productName.orEmpty(),
        productImage = this.productImage.orEmpty(),
        productPrice = this.productPrice.orEmpty(),
        productDescription = this.productDescription.orEmpty(),
        restaurantId = this.restaurantId.orEmpty(),
        restaurantName = this.restaurantName.orEmpty(),
        restaurantLogo = this.restaurantLogo.orEmpty(),
    )
}