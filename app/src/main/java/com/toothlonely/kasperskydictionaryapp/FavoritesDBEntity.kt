package com.toothlonely.kasperskydictionaryapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoritesDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val word: String,
)