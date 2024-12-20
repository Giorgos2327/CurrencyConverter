package com.aliric.converterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.aliric.converterapp.repository.CurrencyRepository
import com.aliric.converterapp.screen.CurrencyItem
import com.aliric.converterapp.ui.theme.ConverterAppTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        val repository=CurrencyRepository()

        val viewModelFactory=CurrencyViewModelFactory(repository)


        val viewModel=ViewModelProvider(this,viewModelFactory)[CurrencyViewModel::class.java]



        setContent {
            ConverterAppTheme {
                CurrencyItem(viewModel)


            }
        }
    }
}

