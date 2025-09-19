package com.toothlonely.kasperskydictionaryapp

import kotlinx.serialization.Serializable

@Serializable
data class Meaning(
    val id: Int,
    val translation: TranslationWord,
)
