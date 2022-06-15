package com.example.restaurants.features.restaurants.presentation.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.restaurants.R
import com.example.restaurants.features.restaurants.domain.entity.Restaurant
import com.example.restaurants.features.restaurants.domain.entity.Specialization

@Composable
fun RestaurantItem(
    restaurant: Restaurant
) {
    val text: StringBuilder = StringBuilder()
    for (i in 0 until restaurant.specializations.size) {
        text.append(
            restaurant.specializations[i].name +
                    if (i < restaurant.specializations.size - 1) " / " else ""
        )
    }
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(CenterVertically)
                    .size(75.dp),
                painter = rememberAsyncImagePainter(
                    error = painterResource(id = R.drawable.ic_no_logo),
                    model = restaurant.logo
                ),
                contentDescription = "",
                tint = Color.Unspecified
            )
            Column(
                modifier = Modifier
                    .align(CenterVertically)
            ) {
                Text(
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = 20.sp,
                    text = restaurant.name
                )
                Text(
                    text = text.toString(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Row {
                    Icon(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(18.dp)
                            .align(CenterVertically),
                        painter = painterResource(
                            id = if (restaurant.positiveReviews.toInt() > 50) R.drawable.ic_thumb_up
                            else R.drawable.ic_thumb_down
                        ),
                        contentDescription = "",
                        tint = colorResource(id = R.color.bottom_bar_color)
                    )
                    Text(
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        fontSize = 18.sp,
                        text = restaurant.positiveReviews + "%"
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun RestaurantPreview() {
    RestaurantItem(
        restaurant = Restaurant(
            name = "ПиццаСушиБорщ",
            logo = "https://static.chibbis.ru/Images/Restaurants/Архангельск/AGAMA/app/logo.jpg",
            minCost = "120",
            deliveryCost = "100",
            deliveryTime = "60",
            positiveReviews = "70",
            reviewsCount = "11",
            specializations = listOf(
                Specialization("Пицца"),
                Specialization("Суши"),
                Specialization("Борщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщщ")
            )
        )
    )
}