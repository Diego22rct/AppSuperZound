package com.github.diego22rct.appsuperzound.list_album.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.diego22rct.appsuperzound.common.Resource
import com.github.diego22rct.appsuperzound.list_album.data.repository.AlbumRepository
import com.github.diego22rct.appsuperzound.list_album.domain.Album
import kotlinx.coroutines.launch

class ListAlbumViewModel(private val repository: AlbumRepository) : ViewModel() {
    private val _state = mutableStateOf<List<Album>>(emptyList())
    val state: MutableState<List<Album>> get() = _state

    private val _favoriteAlbums = mutableStateOf<List<Album>>(emptyList())
    val favoriteAlbums: State<List<Album>> get() = _favoriteAlbums

    init {
        fetchAlbums()
        fetchFavoriteAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            when (val result = repository.getAlbums()) {
                is Resource.Success -> {
                    _state.value = result.data ?: emptyList()
                }
                is Resource.Error -> {
                    // Handle the error case
                }
            }
        }
    }

    private fun fetchFavoriteAlbums() {
        viewModelScope.launch {
            _favoriteAlbums.value = repository.getFavoriteAlbums()
        }
    }

    fun toggleFavorite(album: Album) {
        viewModelScope.launch {
            if (album.isFavorite) {
                repository.removeFavoriteAlbum(album)
            } else {
                repository.addFavoriteAlbum(album)
            }
            fetchFavoriteAlbums()
        }
    }
}