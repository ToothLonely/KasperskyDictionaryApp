package com.toothlonely.kasperskydictionaryapp

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class WordsRepository {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = Retrofit.Builder()
        .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
        .addConverterFactory(
            json.asConverterFactory("application/json; charset=UTF8".toMediaType())
        )
        .build()

    private val service = client.create(WordsAPIService::class.java)

    suspend fun getWord(word: String): String? {

        val result = service.getWords(word)

        return result.execute().body()?.getOrNull(0)?.meanings?.getOrNull(0)?.translation?.text
    }

}