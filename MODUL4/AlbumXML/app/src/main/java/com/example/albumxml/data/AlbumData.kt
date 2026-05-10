package com.example.albumxml.data
import com.example.albumxml.R
import com.example.albumxml.model.Album

object AlbumData {
    val albums = listOf(
        Album(
            1,
            "For All the Dogs",
            "Drake",
            "2023",
            R.drawable.fatg,
            "https://open.spotify.com/album/4czdORdCWP9umpbhFXK2fW?si=Sjm10JYmT56cJ894-6svlA"),
        Album(
            2,
            "Ok Computer",
            "Radiohead",
            "1997",
            R.drawable.ok_computer,
            "https://open.spotify.com/album/6dVIqQ8qmQ5GBnJ9shOYGE?si=hMoKqNtARP2qcicKIQF7sg"),
        Album(
            3,
            "NEVER ENOUGH",
            "Daniel Caesar",
            "2023",
            R.drawable.never_enough,
            "https://open.spotify.com/album/2z9lM6LDS58F70IGyQ1XMK?si=rn3g_5uJSRKYMHMuYdUKpg"),
        Album(
            4,
            "WASTELAND",
            "Brent Faiyaz",
            "2022",
            R.drawable.wasteland,
            "https://open.spotify.com/album/0PHMNbcgHfzSUALlfk7wGg?si=GwV4EUaAScyOWCPP4X1Xig"),
        Album(
            5,
            "DAMN.",
            "Kendrick Lamar",
            "2017",
            R.drawable.damn,
            "https://open.spotify.com/album/4eLPsYPBmXABThSJ821sqY?si=MbbgBE4DSPi_FqBDn06sbA")
    )
}