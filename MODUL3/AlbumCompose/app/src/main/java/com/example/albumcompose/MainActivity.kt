package com.example.albumcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import com.example.albumcompose.ui.view.HomeScreen
import com.example.albumcompose.ui.view.DetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {

                composable("home") {
                    HomeScreen(navController)
                }

                composable("detail/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments
                        ?.getString("id")
                        ?.toIntOrNull() ?: 0

                    DetailScreen(id)
                }
            }
        }
    }
}