package com.toothlonely.kasperskydictionaryapp.model

import com.toothlonely.kasperskydictionaryapp.data.favorites.FavoritesDBEntity

data class Favorites(
    val id: Int = 0,
    val word: String,
) {
    fun toFavoritesEntity() = FavoritesDBEntity(
        id = id,
        word = word,
    )
}