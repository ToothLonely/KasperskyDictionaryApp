package com.toothlonely.kasperskydictionaryapp.model

import com.toothlonely.kasperskydictionaryapp.data.words.WordsDBEntity

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
