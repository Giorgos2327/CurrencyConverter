package com.aliric.converterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliric.converterapp.repository.CurrencyRepository


class CurrencyViewModelFactory(private val repository: CurrencyRepository):ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CurrencyViewModel(repository)as T
        }
        throw IllegalArgumentException("Unknown View Model Class    ")
    }
}