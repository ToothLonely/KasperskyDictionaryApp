package com.toothlonely.kasperskydictionaryapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WordsAPIService {

    @GET("meanings")
    fun getMeanings(@Query("ids") ids: String): Call<List<Meaning>>

    @GET("words/search")
    fun getWords(@Query("search") word: String): Call<List<Word>>
}