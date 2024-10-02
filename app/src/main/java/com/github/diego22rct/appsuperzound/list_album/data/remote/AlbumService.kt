package com.github.diego22rct.appsuperzound.list_album.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("mostloved.php")
    suspend fun getAlbums(@Query("format") format: String = "album"): Response<AlbumResponse>
}