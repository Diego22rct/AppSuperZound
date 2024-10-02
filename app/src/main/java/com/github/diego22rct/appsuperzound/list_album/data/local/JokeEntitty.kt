package com.github.diego22rct.appsuperzound.list_album.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumsEntity(
    @PrimaryKey
    val idAlbum: String,
    val strAlbum: String,
    val strArtist: String,
    val strAlbumThumb: String,
    val intScore: String
)