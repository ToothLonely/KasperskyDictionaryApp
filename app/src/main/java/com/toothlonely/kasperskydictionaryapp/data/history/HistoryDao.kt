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
        SELECT h.id, w.english AS word
        FROM history AS h 
        JOIN words AS w ON h.word = w.english 
    """)
    suspend fun getHistory(): List<History>

    @Query("""
        DELETE FROM history 
        WHERE id = :id
    """)
    suspend fun deleteFromHistory(id: Int)

}