package com.toothlonely.kasperskydictionaryapp

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    version = 1,
    entities = [
        HistoryDBEntity::class,
        FavoritesDBEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao

    abstract fun getFavoritesDao(): FavoritesDao
}
