package com.github.diego22rct.appsuperzound.list_album.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ListFavouriteScreen(viewModel: ListAlbumViewModel) {
    val favoriteAlbums = viewModel.favoriteAlbums.value

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Favourite List")
            Text("App Super Zound")

            LazyColumn {
                items(favoriteAlbums) { album ->
                    AlbumCard(album, onFavoriteClick = { viewModel.toggleFavorite(it) })
                }
            }
        }
    }
}