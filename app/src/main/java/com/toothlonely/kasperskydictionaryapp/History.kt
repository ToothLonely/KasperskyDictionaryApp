package com.toothlonely.kasperskydictionaryapp

data class History(
    val id: Int,
    val word: String,
) {
    fun toHistoryEntity() = HistoryDBEntity(
        id = id,
        word = word,
    )
}
