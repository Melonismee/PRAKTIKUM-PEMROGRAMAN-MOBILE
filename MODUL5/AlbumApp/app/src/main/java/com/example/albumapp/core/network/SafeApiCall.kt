package com.example.albumapp.core.network

import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (e: IOException) {
        ApiResult.Error(
            message = "Tidak ada koneksi internet",
            throwable = e
        )
    } catch (e: Exception) {
        ApiResult.Error(
            message = e.message ?: "Terjadi kesalahan",
            throwable = e
        )
    }
}