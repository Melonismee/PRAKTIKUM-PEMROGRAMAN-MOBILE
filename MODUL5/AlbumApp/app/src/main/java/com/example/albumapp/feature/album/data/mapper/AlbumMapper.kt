package com.example.albumapp.feature.album.data.mapper

import com.example.albumapp.feature.album.data.local.AlbumEntity
import com.example.albumapp.feature.album.data.remote.AlbumDto
import com.example.albumapp.feature.album.domain.model.Album

fun AlbumDto.toEntity(): AlbumEntity {
    return AlbumEntity(
        id = idAlbum.orEmpty(),
        title = strAlbum.orEmpty(),
        artist = strArtist.orEmpty(),
        year = intYearReleased.orEmpty(),
        genre = strGenre.orEmpty(),
        description = strDescriptionEN.orEmpty(),
        imageUrl = strAlbumThumb.orEmpty()
    )
}

fun AlbumEntity.toDomain(): Album {
    return Album(
        id = id,
        title = title,
        artist = artist,
        year = year,
        genre = genre,
        description = description,
        imageUrl = imageUrl
    )
}