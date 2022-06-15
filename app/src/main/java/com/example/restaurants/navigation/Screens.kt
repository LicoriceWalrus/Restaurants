package com.example.restaurants.navigation

import com.example.restaurants.R

sealed class Screens(var title: String, val route: String, val icon: Int) {
    object Restaurants :
        Screens(title = "Рестораны", route = "restaurants_screen", R.drawable.ic_restaurants_menu)

    object Hits : Screens(title = "Хиты", route = "hits_screen", R.drawable.ic_hits_menu)
    object Review : Screens(title = "Отзывы", route = "review_screen", R.drawable.ic_reviews_menu)
}