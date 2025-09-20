package com.toothlonely.kasperskydictionaryapp.data.api

import com.toothlonely.kasperskydictionaryapp.model.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun getWords(@Query("search") word: String): List<Word>
}