package com.toothlonely.kasperskydictionaryapp.data.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toothlonely.kasperskydictionaryapp.model.History

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