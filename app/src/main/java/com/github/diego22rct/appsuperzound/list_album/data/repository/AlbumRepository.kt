package com.github.diego22rct.appsuperzound.list_album.data.repository

import com.github.diego22rct.appsuperzound.common.Resource
import com.github.diego22rct.appsuperzound.list_album.data.local.AlbumDao
import com.github.diego22rct.appsuperzound.list_album.data.local.AlbumEntity
import com.github.diego22rct.appsuperzound.list_album.data.remote.AlbumService
import com.github.diego22rct.appsuperzound.list_album.data.remote.toAlbum
import com.github.diego22rct.appsuperzound.list_album.domain.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepository(private val service: AlbumService, private val albumDao: AlbumDao) {

    suspend fun getAlbums(): Resource<List<Album>> = withContext(Dispatchers.IO) {
        try {
            val response = service.getAlbums()
            if (response.isSuccessful) {
                response.body()?.let { albumResponse ->
                    val albums = albumResponse.loved.map { it.toAlbum() }
                    return@withContext Resource.Success(data = albums)
                }
                return@withContext Resource.Error(response.message())
            }
            return@withContext Resource.Error(response.message())
        } catch (e: Exception) {
            return@withContext Resource.Error(e.message ?: "An error occurred")
        }
    }

    suspend fun addFavoriteAlbum(album: Album) {
        val albumEntity = AlbumEntity(album.idAlbum, album.strAlbum, album.strArtist, album.strAlbumThumb, album.intScore)
        albumDao.insert(albumEntity)
    }

    suspend fun removeFavoriteAlbum(album: Album) {
        val albumEntity = AlbumEntity(album.idAlbum, album.strAlbum, album.strArtist, album.strAlbumThumb, album.intScore)
        albumDao.delete(albumEntity)
    }

    suspend fun getFavoriteAlbums(): List<Album> = withContext(Dispatchers.IO) {
        albumDao.fetchAll().map { Album(it.idAlbum, it.strAlbum, it.strArtist, it.strAlbumThumb, it.intScore, true) }
    }
}