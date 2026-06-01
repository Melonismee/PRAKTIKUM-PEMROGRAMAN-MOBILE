package com.example.albumapp.feature.album.data.repository

import com.example.albumapp.core.network.ApiResult
import com.example.albumapp.core.network.safeApiCall
import com.example.albumapp.feature.album.data.local.AlbumDao
import com.example.albumapp.feature.album.data.mapper.toDomain
import com.example.albumapp.feature.album.data.mapper.toEntity
import com.example.albumapp.feature.album.data.remote.AlbumApiService
import com.example.albumapp.feature.album.domain.model.Album
import com.example.albumapp.feature.album.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class AlbumRepositoryImpl(
    private val apiService: AlbumApiService,
    private val albumDao: AlbumDao
) : AlbumRepository {

    override fun getAlbums(artistName: String): Flow<ApiResult<List<Album>>> = flow {
        Timber.d("Loading albums for artist: $artistName")
        emit(ApiResult.Loading)

        val cachedAlbums = albumDao.getAlbums()
            .map { entities ->
                entities
                    .map { it.toDomain() }
                    .sortedByDescending { it.year.toIntOrNull() ?: 0 }
            }
            .first()
        Timber.d("Cached albums count: ${cachedAlbums.size}")

        if (cachedAlbums.isNotEmpty()) {
            emit(ApiResult.Success(cachedAlbums))
        }

        val result = safeApiCall {
            apiService.searchAlbums(artistName)
        }

        when (result) {
            is ApiResult.Success -> {
                Timber.d("API success for artist: $artistName")
                val albumEntities = result.data.album
                    ?.filter { !it.idAlbum.isNullOrBlank() }
                    ?.map { it.toEntity() }
                    ?.sortedByDescending { it.year.toIntOrNull() ?: 0 }
                    ?: emptyList()
                Timber.d("Album from API count: ${albumEntities.size}")

                albumDao.clearAlbums()
                albumDao.insertAlbums(albumEntities)

                val newAlbums = albumDao.getAlbums()
                    .map { entities -> entities.map { it.toDomain() } }
                    .first()

                emit(ApiResult.Success(newAlbums))
            }

            is ApiResult.Error -> {
                Timber.e(result.throwable, "API error: ${result.message}")
                if (cachedAlbums.isEmpty()) {
                    emit(ApiResult.Error(result.message, result.throwable))
                }
            }

            ApiResult.Loading -> Unit
        }
    }

    override suspend fun getAlbumById(albumId: String): Album? {
        return albumDao.getAlbumById(albumId)?.toDomain()
    }
}