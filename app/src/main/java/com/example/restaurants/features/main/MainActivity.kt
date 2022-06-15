package com.example.restaurants.features.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.restaurants.navigation.BottomNavigation
import com.example.restaurants.navigation.NavigationGraph

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenMainView()
        }
    }

    @Composable
    fun ScreenMainView() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigation(navController = navController) }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(
                    PaddingValues(0.dp, 0.dp, 0.dp, innerPadding.calculateBottomPadding())
                )
            ) {
                NavigationGraph(navController = navController)
            }

        }
    }
}
