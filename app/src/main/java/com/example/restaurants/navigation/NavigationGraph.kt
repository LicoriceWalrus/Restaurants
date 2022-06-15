package com.example.restaurants.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurants.features.hits.presentation.ScreenHits
import com.example.restaurants.features.restaurants.presentation.ScreenRestaurants
import com.example.restaurants.features.reviews.presentation.ScreenReviews

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController, startDestination = Screens.Restaurants.route) {

        composable(Screens.Restaurants.route) {
            ScreenRestaurants()
        }
        composable(Screens.Hits.route) {
            ScreenHits()
        }
        composable(Screens.Review.route) {
            ScreenReviews()
        }
    }
}