package com.example.albumapp.feature.album.data.remote
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApiService {

    @GET("searchalbum.php")
    suspend fun searchAlbums(
        @Query("s") artistName: String
    ): AlbumResponseDto
}