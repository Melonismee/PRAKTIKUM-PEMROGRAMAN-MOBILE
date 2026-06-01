package com.example.albumapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.albumapp.feature.album.presentation.screens.AlbumDetailScreen
import com.example.albumapp.feature.album.presentation.screens.AlbumListScreen
import com.example.albumapp.feature.album.presentation.viewModel.AlbumViewModel
import com.example.albumapp.feature.album.presentation.viewModel.AlbumViewModelFactory

@Composable
fun AppNavigation(
    albumViewModelFactory: AlbumViewModelFactory
) {
    val navController = rememberNavController()

    val albumViewModel: AlbumViewModel = viewModel(
        factory = albumViewModelFactory
    )

    NavHost(
        navController = navController,
        startDestination = Screen.AlbumList.route
    ) {
        composable(route = Screen.AlbumList.route) {
            AlbumListScreen(
                viewModel = albumViewModel,
                onAlbumClick = { albumId ->
                    navController.navigate(Screen.AlbumDetail.createRoute(albumId))
                }
            )
        }

        composable(
            route = Screen.AlbumDetail.route,
            arguments = listOf(
                navArgument("albumId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId").orEmpty()

            AlbumDetailScreen(
                albumId = albumId,
                viewModel = albumViewModel,
            )
        }
    }
}