package com.toothlonely.kasperskydictionaryapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Insert(entity = HistoryDBEntity::class)
    suspend fun addInHistory(newHistory: HistoryDBEntity)

    @Query("""
        SELECT * FROM history
    """)
    suspend fun getHistory(): List<History>

    @Query("""
        DELETE FROM history 
        WHERE id = :id
    """)
    suspend fun deleteFromHistory(id: Int)

}