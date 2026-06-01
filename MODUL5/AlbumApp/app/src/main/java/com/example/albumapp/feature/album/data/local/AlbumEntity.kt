package com.example.albumapp.feature.album.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val artist: String,
    val year: String,
    val genre: String,
    val description: String,
    val imageUrl: String
)