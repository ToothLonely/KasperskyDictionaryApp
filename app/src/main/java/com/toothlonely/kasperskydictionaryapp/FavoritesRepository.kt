package com.toothlonely.kasperskydictionaryapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepository(private val favoritesDao: FavoritesDao) {

    suspend fun addInFavorites(newFavoriteWord: FavoritesDBEntity) = withContext(Dispatchers.IO) {
        favoritesDao.addInFavorites(newFavoriteWord)
    }

    suspend fun getFavorites(): List<String> = withContext(Dispatchers.IO) {
        favoritesDao.getFavorites()
    }

    suspend fun deleteFromFavorites(id: Int) = withContext(Dispatchers.IO) {
        favoritesDao.deleteFromFavorites(id)
    }

}