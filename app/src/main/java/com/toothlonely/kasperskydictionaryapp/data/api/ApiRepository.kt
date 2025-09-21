package com.toothlonely.kasperskydictionaryapp.data.api

import com.toothlonely.kasperskydictionaryapp.data.BASE_URL
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class ApiRepository() {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            json.asConverterFactory("application/json; charset=UTF8".toMediaType())
        )
        .build()

    private val service = client.create(ApiService::class.java)

    suspend fun getWord(word: String): String? {
        return try {
            val resultBody = service.getWords(word)
            resultBody.getOrNull(0)?.meanings?.getOrNull(0)?.translation?.text ?: ""
        } catch (e: Exception) {
            null
        }
    }
}