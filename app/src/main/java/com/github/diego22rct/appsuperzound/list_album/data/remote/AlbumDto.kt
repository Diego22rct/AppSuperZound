package com.github.diego22rct.appsuperzound.list_album.data.remote

import com.github.diego22rct.appsuperzound.list_album.domain.Album

data class AlbumResponse(
    val loved: List<AlbumDto>
)

data class AlbumDto(
    val idAlbum: String,
    val strAlbum: String,
    val strArtist: String,
    val strAlbumThumb: String,
    val intScore: String
)

fun AlbumDto.toAlbum() = Album(
    idAlbum,strAlbum,strArtist, strAlbumThumb, intScore
)