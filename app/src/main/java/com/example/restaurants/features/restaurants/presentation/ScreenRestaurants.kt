package com.example.restaurants.features.restaurants.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.restaurants.R
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
        refresh = vm::refresh,
        onQueryChanged = vm::onQueryChanged,
        searchByName = vm::searchByName
    )
}

@Composable
private fun Restaurants(
    state: State<RestaurantsScreenState>,
    refresh: () -> Unit,
    onQueryChanged: (String) -> Unit,
    searchByName: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current
    DisposableEffect(key1 = null) {
        onDispose {
            searchByName("")
            onQueryChanged("")
        }
    }
    LoadingBox(
        loading = state.value.loading,
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(state.value.isRefreshing),
            onRefresh = { refresh() }
        ) {
            Column {
                TextField(
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .clickable(
                                    enabled = !state.value.query.isNullOrEmpty()
                                ) {
                                    searchByName("")
                                    onQueryChanged("")
                                },
                            tint = colorResource(id = R.color.bottom_bar_color),
                            imageVector = Icons.Default.Close, contentDescription = ""
                        )
                    },
                    enabled = !state.value.loading,
                    leadingIcon = {
                        Icon(
                            tint = colorResource(id = R.color.bottom_bar_color),
                            imageVector = Icons.Default.Search, contentDescription = ""
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp, start = 8.dp, end = 8.dp),
                    value = state.value.query ?: "",
                    onValueChange = onQueryChanged,
                    label = {
                        Text(
                            text = "Введите название заведения"
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = colorResource(id = R.color.bottom_bar_color),
                        focusedLabelColor = colorResource(id = R.color.bottom_bar_color),
                        unfocusedLabelColor = colorResource(id = R.color.bottom_bar_color),
                        backgroundColor = Color.Transparent,
                        cursorColor = colorResource(id = R.color.bottom_bar_color)
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            state.value.query?.let {
                                if (it.isNotEmpty()) {
                                    searchByName(it)
                                }
                            }
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                )
                LazyColumn(
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
        refresh = {},
        onQueryChanged = {},
        searchByName = {}
    )
}