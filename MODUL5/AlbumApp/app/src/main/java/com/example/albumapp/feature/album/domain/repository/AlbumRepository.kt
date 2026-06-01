package com.example.albumapp.feature.album.domain.repository

import com.example.albumapp.core.network.ApiResult
import com.example.albumapp.feature.album.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun getAlbums(artistName: String): Flow<ApiResult<List<Album>>>
    suspend fun getAlbumById(albumId: String): Album?
}