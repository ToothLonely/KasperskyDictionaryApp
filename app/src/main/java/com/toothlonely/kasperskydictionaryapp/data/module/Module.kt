package com.toothlonely.kasperskydictionaryapp.data.module

import android.app.Application
import androidx.room.Room
import com.toothlonely.kasperskydictionaryapp.data.AppDatabase
import com.toothlonely.kasperskydictionaryapp.data.api.ApiRepository
import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesDao
import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesRepository
import com.toothlonely.kasperskydictionaryapp.data.history.HistoryDao
import com.toothlonely.kasperskydictionaryapp.data.history.HistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideApiRepository() = ApiRepository()

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "dictionary.db"
        ).build()
    }

    @Provides
    fun provideFavoritesDao(db: AppDatabase) = db.getFavoritesDao()

    @Provides
    @Singleton
    fun provideFavoritesRepository(dao: FavoritesDao) = FavoritesRepository(dao)

    @Provides
    fun provideHistoryDao(db: AppDatabase) = db.getHistoryDao()

    @Provides
    @Singleton
    fun provideHistoryRepository(dao: HistoryDao) = HistoryRepository(dao)
}