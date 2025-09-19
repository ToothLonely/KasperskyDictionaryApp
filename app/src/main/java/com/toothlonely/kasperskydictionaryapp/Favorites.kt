package com.toothlonely.kasperskydictionaryapp

data class Favorites(
    val id: Int,
    val word: String,
) {
    fun toFavoritesEntity() = FavoritesDBEntity(
        id = id,
        word = word,
    )
}
