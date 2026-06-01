package com.example.albumapp.feature.album.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.albumapp.core.common.Constants
import com.example.albumapp.core.network.ApiResult
import com.example.albumapp.core.preferences.AppPreferences
import com.example.albumapp.feature.album.domain.model.Album
import com.example.albumapp.feature.album.domain.usecase.GetAlbumByIdUseCase
import com.example.albumapp.feature.album.domain.usecase.GetAlbumsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AlbumUiState(
    val isLoading: Boolean = false,
    val albums: List<Album> = emptyList(),
    val selectedAlbum: Album? = null,
    val errorMessage: String? = null,
    val searchQuery: String = Constants.DEFAULT_ARTIST,
    val lastOpenedAlbumTitle: String = ""
)

class AlbumViewModel(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase,
    private val preferences: AppPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        AlbumUiState(
            searchQuery = preferences.getLastArtist(),
            lastOpenedAlbumTitle = preferences.getLastOpenedAlbumTitle()
        )
    )
    val uiState: StateFlow<AlbumUiState> = _uiState

    init {
        getAlbums(preferences.getLastArtist())
    }

    fun getAlbums(artistName: String) {
        if (artistName.isBlank()) return

        preferences.saveLastArtist(artistName)

        viewModelScope.launch {
            getAlbumsUseCase(artistName).collect { result ->
                when (result) {
                    ApiResult.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                    }

                    is ApiResult.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            albums = result.data,
                            errorMessage = null
                        )
                    }

                    is ApiResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    fun getAlbumById(albumId: String) {
        viewModelScope.launch {
            val album = getAlbumByIdUseCase(albumId)

            if (album != null) {
                preferences.saveLastOpenedAlbum(album.id, album.title)
            }

            _uiState.value = _uiState.value.copy(
                selectedAlbum = album,
                lastOpenedAlbumTitle = album?.title ?: preferences.getLastOpenedAlbumTitle()
            )
        }
    }
}

class AlbumViewModelFactory(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase,
    private val preferences: AppPreferences
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(
            getAlbumsUseCase = getAlbumsUseCase,
            getAlbumByIdUseCase = getAlbumByIdUseCase,
            preferences = preferences
        ) as T
    }
}