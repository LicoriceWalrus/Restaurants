package com.example.restaurants.navigation

sealed class Screens(var title: String, val route: String) {
    object Restaurants : Screens(title = "Рестораны", route = "restaurants_screen")
    object Hits : Screens(title = "Хиты", route = "hits_screen")
    object Review : Screens(title = "Отзывы", route = "review_screen")
}