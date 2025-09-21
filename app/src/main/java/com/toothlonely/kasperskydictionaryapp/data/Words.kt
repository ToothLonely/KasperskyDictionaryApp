package com.toothlonely.kasperskydictionaryapp.data

data class Words(
    val id: Int = 0,
    val english: String,
    val russian: String,
)

fun Words.toWordsEntity() = WordsDBEntity(
    id = id,
    english = english,
    russian = russian,
)
