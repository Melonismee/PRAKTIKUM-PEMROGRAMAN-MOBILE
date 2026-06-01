package com.example.albumapp.feature.album.domain.usecase

import com.example.albumapp.feature.album.domain.repository.AlbumRepository

class GetAlbumsUseCase(
    private val repository: AlbumRepository
) {
    operator fun invoke(artistName: String) = repository.getAlbums(artistName)
}