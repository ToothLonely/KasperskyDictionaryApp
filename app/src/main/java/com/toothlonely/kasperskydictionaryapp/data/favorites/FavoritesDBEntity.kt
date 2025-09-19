package com.toothlonely.kasperskydictionaryapp.data.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
)