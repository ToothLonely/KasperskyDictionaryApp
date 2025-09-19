package com.toothlonely.kasperskydictionaryapp

data class Favorites(
    val id: Int = 0,
    val word: String,
) {
    fun toFavoritesEntity() = FavoritesDBEntity(
        id = id,
        word = word,
    )
}
