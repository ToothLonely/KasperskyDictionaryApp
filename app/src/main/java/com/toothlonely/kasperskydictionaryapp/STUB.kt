package com.toothlonely.kasperskydictionaryapp

object STUB {
    private val history = mutableListOf<String>()

    private val favorites = mutableListOf<String>()

    fun getHistoryList(): List<String> {
        return history
    }

    fun addNewWordInHistory(newWord: String) {
        history.add(newWord)
    }

    fun deleteWordFromHistory(word: String) {
        history.remove(word)
    }

    fun addInFavorites(word: String) {
        favorites.add(word)
    }

    fun getFavorites(): List<String> {
        return favorites
    }

    fun deleteFromFavorites(word: String) {
        favorites.remove(word)
    }

}