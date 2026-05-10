package com.example.albumcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.albumcompose.data.AlbumData
import com.example.albumcompose.model.Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class AlbumViewModel(
    private val username: String
) : ViewModel() {
    private val _albumList =
        MutableStateFlow<List<Album>>(emptyList())

    val albumList: StateFlow<List<Album>>
            = _albumList

    private val _selectedAlbum =
        MutableStateFlow<Album?>(null)

    val selectedAlbum: StateFlow<Album?>
            = _selectedAlbum

    init {
        loadAlbums()
    }

    private fun loadAlbums() {

        _albumList.value = AlbumData.albums

        Timber.d(
            "Data album berhasil dimasukkan oleh $username"
        )

        _albumList.value.forEach { album ->

            Timber.d(
                "Album: ${album.title}"
            )
        }
    }

    fun selectAlbum(album: Album) {

        _selectedAlbum.value = album

        Timber.d(
            "Album dipilih: ${album.title}"
        )
    }

    fun detailButtonClicked(album: Album) {

        Timber.d(
            "Tombol Detail ditekan: ${album.title}"
        )
    }

    fun explicitIntentClicked(album: Album) {

        Timber.d(
            "Tombol Explicit Intent ditekan: ${album.title}"
        )
    }
}

class AlbumViewModelFactory(
    private val username: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                AlbumViewModel::class.java
            )
        ) {

            return AlbumViewModel(username) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}