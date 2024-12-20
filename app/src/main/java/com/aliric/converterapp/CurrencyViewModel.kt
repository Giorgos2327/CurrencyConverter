package com.aliric.converterapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliric.converterapp.json.Rates
import com.aliric.converterapp.repository.CurrencyRepository
import kotlinx.coroutines.launch

class CurrencyViewModel(repository: CurrencyRepository):ViewModel() {


    var rates by mutableStateOf<Rates?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)


    init {
        viewModelScope.launch {
            isLoading=true
            errorMessage=null
            try {
                rates=repository.getRatesFromApi("fa2e7243c6242145120bb147406192b3")
            }catch (e:Exception){
                errorMessage="Failed to load rates:${e.message}"
            }finally {
                isLoading=false
            }



        }
    }


}