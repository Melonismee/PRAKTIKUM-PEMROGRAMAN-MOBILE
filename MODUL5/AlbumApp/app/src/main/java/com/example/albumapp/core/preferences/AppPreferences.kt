package com.example.albumapp.core.preferences

import android.content.Context
import com.example.albumapp.core.common.Constants

class AppPreferences(context: Context) {

    private val preferences = context.getSharedPreferences(
        "album_preferences",
        Context.MODE_PRIVATE
    )

    fun saveLastArtist(artistName: String) {
        preferences.edit()
            .putString("last_artist", artistName)
            .apply()
    }

    fun getLastArtist(): String {
        return preferences.getString("last_artist", Constants.DEFAULT_ARTIST)
            ?: Constants.DEFAULT_ARTIST
    }

    fun saveLastOpenedAlbum(albumId: String, albumTitle: String) {
        preferences.edit()
            .putString("last_album_id", albumId)
            .putString("last_album_title", albumTitle)
            .apply()
    }

    fun getLastOpenedAlbumTitle(): String {
        return preferences.getString("last_album_title", "") ?: ""
    }
}