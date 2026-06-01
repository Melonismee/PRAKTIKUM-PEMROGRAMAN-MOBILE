package com.example.albumapp.feature.album.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.albumapp.feature.album.domain.model.Album
import com.example.albumapp.feature.album.presentation.components.AlbumCard
import com.example.albumapp.feature.album.presentation.viewModel.AlbumViewModel
import com.example.albumapp.ui.theme.CardAlbumBackground
import com.example.albumapp.ui.theme.PurplePrimary
import com.example.albumapp.ui.theme.TextSecondary
import timber.log.Timber

@Composable
fun AlbumListScreen(
    viewModel: AlbumViewModel,
    onAlbumClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    fun createAlbumUrl(album: Album): String {
        Timber.d("Open music clicked: ${album.title} - ${album.artist}")
        fun slug(text: String): String {
            return text
                .trim()
                .replace("&", "and")
                .replace(Regex("[^A-Za-z0-9\\s-]"), "")
                .replace(Regex("\\s+"), "-")
        }

        val artistSlug = slug(album.artist)
        val titleSlug = slug(album.title)

        return "https://www.theaudiodb.com/album/${album.id}-$artistSlug-$titleSlug"
    }

    fun openMusic(album: Album) {
        uriHandler.openUri(createAlbumUrl(album))
    }

    when {
        uiState.isLoading && uiState.albums.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.errorMessage != null && uiState.albums.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Gagal memuat album",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = uiState.errorMessage ?: "Terjadi kesalahan")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            viewModel.getAlbums(uiState.searchQuery)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PurplePrimary
                        )
                    ) {
                        Text(text = "Coba Lagi")
                    }
                }
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                item {
                    Text(
                        text = "Featured Albums",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 18.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(uiState.albums.take(5)) { album ->
                            FeaturedAlbumCard(
                                album = album,
                                onOpenMusicClick = {
                                    openMusic(album)
                                },
                                onDetailClick = {
                                    Timber.d("Detail clicked: ${album.title}")
                                    onAlbumClick(album.id)
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "All Albums",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(
                    items = uiState.albums,
                    key = { album -> album.id }
                ) { album ->
                    AlbumCard(
                        album = album,
                        onOpenMusicClick = {
                            openMusic(album)
                        },
                        onDetailClick = {
                            Timber.d("Detail clicked: ${album.title}")
                            onAlbumClick(album.id)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun FeaturedAlbumCard(
    album: Album,
    onOpenMusicClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    Card(
        modifier = Modifier.width(340.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardAlbumBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = album.imageUrl,
                contentDescription = album.title,
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = album.title.ifBlank { "Unknown Album" },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = album.artist.ifBlank { "Unknown Artist" },
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = album.year.ifBlank { "-" },
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onOpenMusicClick,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PurplePrimary
                        ),
                        modifier = Modifier.weight(1.4f)
                    ) {
                        Text(
                            text = "Open Music",
                            maxLines = 1,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Button(
                        onClick = onDetailClick,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PurplePrimary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Detail",
                            maxLines = 1,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}