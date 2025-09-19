package com.toothlonely.kasperskydictionaryapp

class FavoritesRepository(private val favoritesDao: FavoritesDao) {

    suspend fun addInFavorites(newFavoriteWord: FavoritesDBEntity) =
        favoritesDao.addInFavorites(newFavoriteWord)

    suspend fun getFavorites(): List<Favorites> = favoritesDao.getFavorites()

    suspend fun deleteFromFavorites(id: Int) = favoritesDao.deleteFromFavorites(id)

    suspend fun getFavoritesWords(): List<String> = getFavorites().map { it.word }

}