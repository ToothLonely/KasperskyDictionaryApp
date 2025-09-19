package com.toothlonely.kasperskydictionaryapp.data.api

import com.toothlonely.kasperskydictionaryapp.model.Word
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsAPIService {
    @GET("words/search")
    fun getWords(@Query("search") word: String): Call<List<Word>>
}