package com.toothlonely.kasperskydictionaryapp.data.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
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


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideFavoritesRepository(dao: FavoritesDao) = FavoritesRepository(dao)

    @Provides
    fun provideFavoritesDao(db: AppDatabase) = db.getFavoritesDao()

    @Provides
    fun provideHistoryDao(db: AppDatabase) = db.getHistoryDao()

    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "dictionary.db"
        ).build()
    }

    @Provides
    fun provideHistoryRepository(dao: HistoryDao) = HistoryRepository(dao)

    @Provides
    fun provideApiRepository() = ApiRepository()
}