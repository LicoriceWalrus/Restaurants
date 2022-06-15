package com.example.restaurants.features.hits.data.api

import com.example.restaurants.features.hits.data.dto.HitsDto
import retrofit2.Response
import retrofit2.http.GET

interface HitsApi {

    @GET("hits")
    suspend fun getHits(): Response<List<HitsDto>>
}