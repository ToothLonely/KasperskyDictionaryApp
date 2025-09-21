package com.toothlonely.kasperskydictionaryapp.data

class WordsRepository(private val wordsDao: WordsDao) {

    suspend fun addNewWord(newPair: WordsDBEntity) = wordsDao.addNewWords(newPair)
    suspend fun getWords(): List<Words> = wordsDao.getWords()
    suspend fun getEnglishWordById(id: Int): String = wordsDao.getEnglishWordById(id)
    suspend fun getIdByWord(word: String): Int = wordsDao.getIdByWord(word)
    suspend fun isWordExistInDB(word: String) = wordsDao.isWordExist(word)

}