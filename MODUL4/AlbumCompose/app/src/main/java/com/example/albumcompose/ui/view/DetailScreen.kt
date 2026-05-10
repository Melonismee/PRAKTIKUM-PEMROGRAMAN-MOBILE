package com.example.albumcompose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.albumcompose.data.AlbumData
import com.example.albumcompose.ui.theme.AlbumComposeTheme
import com.example.albumcompose.viewmodel.AlbumViewModel
import timber.log.Timber

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {

    AlbumComposeTheme {

        DetailScreen(
            id = 1,
            viewModel = null
        )
    }
}

@Composable
fun DetailScreen(
    id: Int,
    viewModel: AlbumViewModel?
) {

    val selectedAlbum by viewModel
        ?.selectedAlbum
        ?.collectAsState()

        ?: remember {
            mutableStateOf(
                AlbumData.albums.find { it.id == id }
            )
        }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        selectedAlbum?.let { album ->

            LaunchedEffect(Unit) {

                Timber.d(
                    "Masuk ke halaman detail: ${album.title}"
                )
            }

            Image(
                painter = painterResource(album.image),
                contentDescription = album.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = album.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(album.artist)

            Text(album.year)
        }
    }
}