package com.example.restaurants.data

import retrofit2.Response

fun <T> Response<T>.getOrThrow(): T {
    val body = body()
    if (isSuccessful && body != null) {
        return body
    } else {
        throw Exception()
    }
}