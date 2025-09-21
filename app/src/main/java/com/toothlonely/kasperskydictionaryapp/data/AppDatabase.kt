package com.toothlonely.kasperskydictionaryapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesDBEntity
import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesDao
import com.toothlonely.kasperskydictionaryapp.data.history.HistoryDBEntity
import com.toothlonely.kasperskydictionaryapp.data.history.HistoryDao

@Database(
    version = 1,
    entities = [
        HistoryDBEntity::class,
        FavoritesDBEntity::class,
        WordsDBEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao

    abstract fun getFavoritesDao(): FavoritesDao

    abstract fun getWordsDao(): WordsDao
}