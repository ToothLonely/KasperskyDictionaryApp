package com.toothlonely.kasperskydictionaryapp

import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val text: String,
    val meanings: List<Meaning>,
)
