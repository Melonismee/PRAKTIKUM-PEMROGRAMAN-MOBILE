package com.example.albumapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.albumapp.app.navigation.AppNavigation
import com.example.albumapp.core.database.AppDatabase
import com.example.albumapp.core.network.ApiClient
import com.example.albumapp.core.preferences.AppPreferences
import com.example.albumapp.feature.album.data.repository.AlbumRepositoryImpl
import com.example.albumapp.feature.album.domain.usecase.GetAlbumByIdUseCase
import com.example.albumapp.feature.album.domain.usecase.GetAlbumsUseCase
import com.example.albumapp.feature.album.presentation.viewModel.AlbumViewModelFactory
import com.example.albumapp.ui.theme.AlbumAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val albumDao = database.albumDao()

        val repository = AlbumRepositoryImpl(
            apiService = ApiClient.albumApiService,
            albumDao = albumDao
        )

        val getAlbumsUseCase = GetAlbumsUseCase(repository)
        val getAlbumByIdUseCase = GetAlbumByIdUseCase(repository)
        val appPreferences = AppPreferences(applicationContext)

        val albumViewModelFactory = AlbumViewModelFactory(
            getAlbumsUseCase = getAlbumsUseCase,
            getAlbumByIdUseCase = getAlbumByIdUseCase,
            preferences = appPreferences
        )

        setContent {
            AlbumAppTheme {
                AppNavigation(
                    albumViewModelFactory = albumViewModelFactory
                )
            }
        }
    }
}