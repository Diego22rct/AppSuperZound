package com.github.diego22rct.appsuperzound.list_album.domain

data class Album(
    val idAlbum: String,
    val strAlbum: String,
    val strArtist: String,
    val strAlbumThumb: String,
    val intScore: String,
    var isFavorite: Boolean = false
)