package com.toothlonely.kasperskydictionaryapp.data.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
)