package com.aliric.converterapp.repository

import android.content.Context
import com.aliric.converterapp.api.RetrofitInstance
import com.aliric.converterapp.json.Rates

class CurrencyRepository() {

    suspend fun getRatesFromApi(apiKey:String):Rates{

        return RetrofitInstance.api.getRates(apiKey).rates
    }
}