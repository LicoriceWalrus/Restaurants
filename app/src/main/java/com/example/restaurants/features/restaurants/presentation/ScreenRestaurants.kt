package com.example.restaurants.features.restaurants.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurants.components.LoadingBox
import com.example.restaurants.features.restaurants.domain.entity.Restaurant
import com.example.restaurants.features.restaurants.domain.entity.Specialization
import com.example.restaurants.features.restaurants.presentation.model.RestaurantItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.flowOf
import org.koin.androidx.compose.getViewModel

@Composable
fun ScreenRestaurants(
    vm: RestaurantsViewModel = getViewModel()
) {
    Restaurants(
        state = vm.screenState().collectAsState(),
        refresh = vm::refresh
    )
}

@Composable
private fun Restaurants(
    state: State<RestaurantsScreenState>,
    refresh: () -> Unit
) {
    LoadingBox(
        loading = state.value.loading,
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(state.value.isRefreshing),
            onRefresh = { refresh() }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 8.dp)
            ) {
                val restaurants = state.value.restaurants
                items(
                    count = restaurants.size,
                    key = null,
                ) { position ->
                    RestaurantItem(
                        restaurant = restaurants[position]
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun RestaurantsPreview() {
    Restaurants(
        state = flowOf<RestaurantsScreenState>().collectAsState(
            initial = RestaurantsScreenState(
                loading = true,
                restaurants = listOf(
                    Restaurant(
                        name = "ПиццаСушиБорщ",
                        logo = "",
                        minCost = "120",
                        deliveryCost = "100",
                        deliveryTime = "60",
                        positiveReviews = "70",
                        reviewsCount = "11",
                        specializations = listOf(
                            Specialization("Пицца"), Specialization("Суши"), Specialization("Борщ")
                        )
                    ),
                    Restaurant(
                        name = "Суши-Мухи",
                        logo = "",
                        minCost = "20",
                        deliveryCost = "1000",
                        deliveryTime = "455",
                        positiveReviews = "1",
                        reviewsCount = "600",
                        specializations = listOf(
                            Specialization("Мухи"), Specialization("Клопы"), Specialization("Соль")
                        )
                    )
                )
            )
        ),
        refresh = {}
    )
}