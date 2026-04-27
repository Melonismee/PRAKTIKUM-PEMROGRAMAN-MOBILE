package com.example.albumcompose.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.albumcompose.R
import com.example.albumcompose.data.AlbumData

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}
@Composable
fun HomeScreen(navController: NavController) {

    val data = AlbumData.albums
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {

        item {
            Text(
                text = stringResource(R.string.featured_albums),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            LazyRow {
                items(data) { album ->
                    Box(
                        modifier = Modifier.width(360.dp)
                    ) {
                        AlbumCard(
                            album = album,
                            context = context,
                            onDetailClick = {
                                navController.navigate("detail/${album.id}")
                            }
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = stringResource(R.string.all_albums),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
        }

        items(data) { album ->
            AlbumCard(
                album = album,
                context = context,
                onDetailClick = {
                    navController.navigate("detail/${album.id}")
                }
            )
        }
    }
}