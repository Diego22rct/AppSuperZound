package com.github.diego22rct.appsuperzound.list_album.data.repository

import com.github.diego22rct.appsuperzound.common.Resource
import com.github.diego22rct.appsuperzound.list_album.data.remote.AlbumService
import com.github.diego22rct.appsuperzound.list_album.data.remote.toAlbum
import com.github.diego22rct.appsuperzound.list_album.domain.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepository(private val service: AlbumService) {
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
}