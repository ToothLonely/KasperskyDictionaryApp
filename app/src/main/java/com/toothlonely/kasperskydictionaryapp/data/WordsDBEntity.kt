package com.toothlonely.kasperskydictionaryapp.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
    indices = [
        Index(value = ["english"], unique = true),
        Index(value = ["russian"], unique = true)
    ]
)
data class WordsDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val english: String,
    val russian: String,
)
