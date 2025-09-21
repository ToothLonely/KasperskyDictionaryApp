package com.toothlonely.kasperskydictionaryapp.data.words

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.toothlonely.kasperskydictionaryapp.model.Words

@Dao
interface WordsDao {

    @Insert(entity = WordsDBEntity::class)
    suspend fun addNewWords(newPair: WordsDBEntity)

    @Query("""
        SELECT * FROM words
    """)
    suspend fun getWords(): List<Words>

    @Query("""
        SELECT english FROM words
        WHERE id = :id
    """)
    suspend fun getEnglishWordById(id: Int): String

    @Query("""
        SELECT id FROM words
        WHERE english = :english
    """)
    suspend fun getIdByWord(english: String): Int

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM words
            WHERE english = :word
        )
    """)
    suspend fun isWordExist(word: String): Boolean

    @Query("""
        SELECT russian FROM words
        WHERE english = :word
    """)
    suspend fun getWordFromLocalDB(word: String): String?

}