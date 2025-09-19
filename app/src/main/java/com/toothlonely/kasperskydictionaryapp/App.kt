package com.toothlonely.kasperskydictionaryapp

import android.app.Application
import androidx.room.Room

class App : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "dictionary.db"
        ).build()
    }

    val historyRepository: HistoryRepository by lazy {
        HistoryRepository(database.getHistoryDao())
    }

    val favoritesRepository: FavoritesRepository by lazy {
        FavoritesRepository(database.getFavoritesDao())
    }
}