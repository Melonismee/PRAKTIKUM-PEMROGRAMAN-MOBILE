package com.example.albumapp.feature.album.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlbumDto(
    @SerialName("idAlbum")
    val idAlbum: String? = null,

    @SerialName("strAlbum")
    val strAlbum: String? = null,

    @SerialName("strArtist")
    val strArtist: String? = null,

    @SerialName("intYearReleased")
    val intYearReleased: String? = null,

    @SerialName("strGenre")
    val strGenre: String? = null,

    @SerialName("strDescriptionEN")
    val strDescriptionEN: String? = null,

    @SerialName("strAlbumThumb")
    val strAlbumThumb: String? = null
)