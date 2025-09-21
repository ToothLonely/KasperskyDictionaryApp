package com.toothlonely.kasperskydictionaryapp.data.words

class WordsRepository(private val wordsDao: WordsDao) {

    suspend fun addNewWord(newPair: WordsDBEntity) = wordsDao.addNewWords(newPair)
    suspend fun isWordExistInDB(word: String) = wordsDao.isWordExist(word)
    suspend fun getWordFromLocalDB(word: String) = wordsDao.getWordFromLocalDB(word)

}