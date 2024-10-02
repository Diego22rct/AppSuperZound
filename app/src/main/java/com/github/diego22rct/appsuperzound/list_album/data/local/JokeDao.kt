package com.github.diego22rct.appsuperzound.list_album.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlbumDao {

    @Insert
    suspend fun insert(jokeEntity: AlbumEntity)

    @Delete
    suspend fun delete(jokeEntity: AlbumEntity)

    @Update
    suspend fun update(jokeEntity: AlbumEntity)

    @Query("select * from albums")
    suspend fun fetchAll(): List<AlbumEntity>

    @Query("select * from albums where idAlbum =:idAlbum")
    suspend fun fetchById(idAlbum: String): AlbumEntity?

}