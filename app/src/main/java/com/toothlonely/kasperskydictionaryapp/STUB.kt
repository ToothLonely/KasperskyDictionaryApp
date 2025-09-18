package com.toothlonely.kasperskydictionaryapp

object STUB {

    private val dictionary = listOf(
        Translation("hello", "привет"),
        Translation("dog", "собака"),
        Translation("cat", "кошка"),
        Translation("house", "дом"),
        Translation("table", "стол"),
        Translation("face", "лицо"),
        Translation("sofa", "диван"),
        Translation("cup", "чашка"),
    )

    private val history = mutableListOf<String>()

    private val favorites = mutableListOf<String>()

    fun getOriginals(): List<String> {
        return dictionary.map {
            it.original
        }
    }

    fun getDictionary(): List<Translation> {
        return dictionary
    }

    fun getHistoryList(): List<String> {
        return history
    }

    fun addNewWordInHistory(newWord: String) {
        history.add(newWord)
    }

    fun getTranslation(originalWord: String): String? {
        return dictionary.find { originalWord == it.original }?.translate?.lowercase()
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