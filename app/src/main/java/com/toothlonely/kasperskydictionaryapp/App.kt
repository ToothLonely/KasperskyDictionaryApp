package com.toothlonely.kasperskydictionaryapp

import android.app.Application
import androidx.room.Room
import com.toothlonely.kasperskydictionaryapp.data.AppDatabase
import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesRepository
import com.toothlonely.kasperskydictionaryapp.data.history.HistoryRepository

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