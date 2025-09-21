package com.toothlonely.kasperskydictionaryapp

import android.app.Application
import androidx.room.Room
import com.toothlonely.kasperskydictionaryapp.data.AppDatabase
import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesRepository
import com.toothlonely.kasperskydictionaryapp.data.history.HistoryRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()