package com.toothlonely.kasperskydictionaryapp.data.favorites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toothlonely.kasperskydictionaryapp.model.Favorites

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