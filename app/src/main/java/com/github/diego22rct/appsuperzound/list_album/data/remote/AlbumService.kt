package com.github.diego22rct.appsuperzound.list_album.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {
    @GET("/")
    suspend fun getAlbums(): Response<AlbumResponse>
}