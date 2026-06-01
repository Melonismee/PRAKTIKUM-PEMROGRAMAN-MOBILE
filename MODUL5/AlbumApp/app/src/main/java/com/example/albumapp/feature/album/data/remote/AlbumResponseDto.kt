package com.example.albumapp.feature.album.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponseDto(
    @SerialName("album")
    val album: List<AlbumDto>? = emptyList()
)