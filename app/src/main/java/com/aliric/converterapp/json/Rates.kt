package com.aliric.converterapp.json

data class Rates(
    val EUR:Double,
    val USD:Double,
    val JPY:Double,
    val AUD:Double,
    val GBP:Double,
){
    fun getRate(currency:String):Double?{
        return when(currency){
            "AUD"->AUD
            "EUR"->EUR
            "JPY"->JPY
            "GBP"->GBP
            "USD"->USD
            else->null
        }
    }
}

