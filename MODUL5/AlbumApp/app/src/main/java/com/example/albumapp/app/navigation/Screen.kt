package com.example.albumapp.app.navigation

sealed class Screen(val route: String) {
    data object AlbumList : Screen("album_list")
    data object AlbumDetail : Screen("album_detail/{albumId}") {
        fun createRoute(albumId: String): String {
            return "album_detail/$albumId"
        }
    }
}