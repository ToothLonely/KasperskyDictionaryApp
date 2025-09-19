package com.toothlonely.kasperskydictionaryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritesDao {

    @Insert(entity = FavoritesDBEntity::class)
    suspend fun addInFavorites(newFavoriteWord: FavoritesDBEntity)

    @Query("""
        SELECT * FROM favorites
    """)
    suspend fun getFavorites(): List<Favorites>

    @Query("""
        DELETE FROM favorites 
        WHERE id = :id
    """)
    suspend fun deleteFromFavorites(id: Int)

}