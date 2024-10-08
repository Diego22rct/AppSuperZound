package com.github.diego22rct.appsuperzound.list_album.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.diego22rct.appsuperzound.list_album.domain.Album

@Composable
fun ListAlbumScreen(viewModel: ListAlbumViewModel) {
    val albums = viewModel.state.value

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("List Album")
            Text("App Super Zound")

            LazyColumn {
                items(albums) { album ->
                    AlbumCard(album, onFavoriteClick = { viewModel.toggleFavorite(it) })
                }
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album, onFavoriteClick: (Album) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = album.strAlbum)
            Text(text = album.strArtist)
            IconButton(onClick = { onFavoriteClick(album) }) {
                Icon(
                    imageVector = if (album.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                    contentDescription = "Favorite"
                )
            }
        }
    }
}
