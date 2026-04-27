package com.example.albumcompose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.albumcompose.data.AlbumData
import com.example.albumcompose.ui.theme.AlbumComposeTheme

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    AlbumComposeTheme {
        DetailScreen(id = 1)
    }
}

@Composable
fun DetailScreen(id: Int) {

    val album = AlbumData.albums.find { it.id == id }

    Column(modifier = Modifier.padding(16.dp)) {

        album?.let {

            Image(
                painter = painterResource(it.image),
                contentDescription = it.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Text(it.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(it.artist)
            Text(it.year)
        }
    }
}