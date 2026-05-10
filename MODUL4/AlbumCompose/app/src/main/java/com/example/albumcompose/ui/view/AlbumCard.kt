package com.example.albumcompose.ui.view

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.albumcompose.R
import com.example.albumcompose.data.AlbumData
import com.example.albumcompose.model.Album
import com.example.albumcompose.viewmodel.AlbumViewModel

@Preview(showBackground = true)
@Composable
fun AlbumCardPreview() {

    val context = LocalContext.current

    AlbumCard(
        album = AlbumData.albums[0],
        context = context,
        viewModel = null,
        onDetailClick = {}
    )
}

@Composable
fun AlbumCard(
    album: Album,
    context: Context,
    viewModel: AlbumViewModel?,
    onDetailClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(album.image),
                contentDescription = null,

                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = album.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(album.artist)
                Text(album.year)

                Spacer(modifier = Modifier.height(8.dp))

                Row {

                    Button(
                        onClick = {

                            viewModel?.explicitIntentClicked(album)

                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                album.link.toUri()
                            )

                            context.startActivity(intent)
                        }
                    ) {

                        Text(stringResource(R.string.open_music))
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {

                            viewModel?.detailButtonClicked(album)

                            viewModel?.selectAlbum(album)

                            onDetailClick()
                        }
                    ) {

                        Text(stringResource(R.string.detail))
                    }
                }
            }
        }
    }
}