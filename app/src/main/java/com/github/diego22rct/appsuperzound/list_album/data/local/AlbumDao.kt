package com.github.diego22rct.appsuperzound.list_album.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: AlbumEntity)

    @Delete
    suspend fun delete(album: AlbumEntity)

    @Query("SELECT * FROM albums")
    suspend fun fetchAll(): List<AlbumEntity>
}