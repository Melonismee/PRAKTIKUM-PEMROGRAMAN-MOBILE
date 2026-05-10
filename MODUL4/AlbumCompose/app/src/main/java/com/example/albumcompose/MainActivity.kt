package com.example.albumcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.albumcompose.ui.view.DetailScreen
import com.example.albumcompose.ui.view.HomeScreen
import com.example.albumcompose.viewmodel.AlbumViewModel
import com.example.albumcompose.viewmodel.AlbumViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: AlbumViewModel by viewModels {
        AlbumViewModelFactory("Ibnu")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "home"
            ) {

                composable("home") {

                    HomeScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }

                composable("detail/{id}") {

                    val id = it.arguments
                        ?.getString("id")
                        ?.toIntOrNull() ?: 0

                    DetailScreen(
                        id = id,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}