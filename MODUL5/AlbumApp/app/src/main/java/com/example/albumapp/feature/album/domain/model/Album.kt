package com.example.albumapp.feature.album.domain.model

data class Album(
    val id: String,
    val title: String,
    val artist: String,
    val year: String,
    val genre: String,
    val description: String,
    val imageUrl: String
)