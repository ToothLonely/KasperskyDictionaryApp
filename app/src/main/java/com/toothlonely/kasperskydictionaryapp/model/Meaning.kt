package com.toothlonely.kasperskydictionaryapp.model

import kotlinx.serialization.Serializable

@Serializable
data class Meaning(
    val id: Int,
    val translation: Translation,
)
