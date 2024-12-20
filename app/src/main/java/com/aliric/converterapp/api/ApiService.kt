package com.aliric.converterapp.api

import com.aliric.converterapp.json.CurrencyModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    suspend fun getRates(
        @Query("access_key") apiKey:String,
    ):CurrencyModel
}