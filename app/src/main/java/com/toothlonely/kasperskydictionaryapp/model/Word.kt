package com.toothlonely.kasperskydictionaryapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val text: String,
    val meanings: List<Meaning>,
)
