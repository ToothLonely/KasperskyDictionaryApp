package com.toothlonely.kasperskydictionaryapp.model

import com.toothlonely.kasperskydictionaryapp.data.history.HistoryDBEntity

data class History(
    val id: Int = 0,
    val word: String,
) {
    fun toHistoryEntity() = HistoryDBEntity(
        id = id,
        word = word,
    )
}