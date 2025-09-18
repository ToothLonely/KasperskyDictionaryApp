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

    fun getOriginals(): List<String> {
        return dictionary.map {
            it.original
        }
    }
}