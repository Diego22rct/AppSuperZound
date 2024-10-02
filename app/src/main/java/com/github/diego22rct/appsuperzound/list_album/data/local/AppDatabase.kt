package com.github.diego22rct.appsuperzound.list_album.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAlbumDao(): AlbumDao
}