package com.example.albumapp.feature.album.domain.usecase

import com.example.albumapp.feature.album.domain.repository.AlbumRepository

class GetAlbumByIdUseCase(
    private val repository: AlbumRepository
) {
    suspend operator fun invoke(albumId: String) = repository.getAlbumById(albumId)
}