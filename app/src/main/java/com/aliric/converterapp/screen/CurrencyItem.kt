package com.aliric.converterapp.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliric.converterapp.CurrencyViewModel
import com.aliric.converterapp.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyItem(viewModel: CurrencyViewModel) {


    val currencies = listOf("EUR", "USD", "AUD", "GBP", "JPY")
    val currencyFlags= mapOf(
        "EUR" to R.drawable.europe,
        "USD" to R.drawable.usa,
        "AUD" to R.drawable.australia,
        "GBP" to R.drawable.england,
        "JPY" to R.drawable.japan
    )

    val context= LocalContext.current

    //States for the selected currencies
    val firstCurrency = remember {
        mutableStateOf(currencies.first())
    }
    val secondCurrency = remember {
        mutableStateOf(currencies.last())
    }


    //State to control visibility of dropdown menus
    val fromCurrency = remember {
        mutableStateOf(false)
    }
    val toCurrency = remember {
        mutableStateOf(false)
    }

    // State for the amount input
    val amount = remember {
        mutableStateOf("")
    }

    //State for the result
    val result = remember {
        mutableStateOf("")
    }



    LaunchedEffect(key1 = Unit) {

        viewModel.rates

    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else if (viewModel.errorMessage != null) {
            Text(text = viewModel.errorMessage!!, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column {
            Text(text = "Currency", fontSize = 30.sp,  fontStyle = FontStyle.Italic, color = Color.Blue, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif)
            Text(text = "Converter", fontSize = 30.sp, fontStyle = FontStyle.Italic,  color = Color.Blue, fontWeight = FontWeight.SemiBold, fontFamily = FontFamily.Serif)
        }
        
        Spacer(modifier = Modifier.height(35.dp))


        TextField(
            value = amount.value,
            onValueChange = { amount.value=it },
            label = { Text(text = "Enter Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Blue,
                containerColor = Color.White,
                unfocusedTextColor = Color.Gray,
            ), shape = RoundedCornerShape(25.dp)

            )

        Spacer(modifier = Modifier.height(35.dp))


        Box {
            OutlinedTextField(
                value = firstCurrency.value,
                onValueChange = { firstCurrency.value = it },
                label = { Text(text = "Select a currency") }, colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = colorResource(id = R.color.placeholder)
                ), shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                   Image(painter = painterResource(id = currencyFlags[firstCurrency.value]?:R.drawable.europe), contentDescription ="", modifier = Modifier.size(40.dp) )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "DropDownArrow",
                        modifier = Modifier.clickable { fromCurrency.value = true })
                }, readOnly = true


            )

            DropdownMenu(
                expanded = fromCurrency.value,
                onDismissRequest = { fromCurrency.value = false })
            {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        text = { Text(text = currency) },
                        onClick = {
                            firstCurrency.value = currency
                            fromCurrency.value = false


                        })
                    



                }

            }
        }
        Spacer(modifier = Modifier.height(25.dp))

        Image(painter = painterResource(id = R.drawable.arrow), contentDescription ="Arrow", modifier = Modifier.size(35.dp) )

        Spacer(modifier = Modifier.height(30.dp))
        Box {
            OutlinedTextField(
                value = secondCurrency.value,
                onValueChange = { secondCurrency.value = it },
                label = { Text(text = "Select a currency") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    containerColor = colorResource(id = R.color.placeholder)
                ), shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Image(painter = painterResource(id = currencyFlags[secondCurrency.value]?:R.drawable.usa), contentDescription ="", modifier = Modifier.size(40.dp) )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "DropDownArrow",
                        modifier = Modifier.clickable { toCurrency.value = true })
                }, readOnly = true


            )

            DropdownMenu(
                expanded = toCurrency.value,
                onDismissRequest = { toCurrency.value = false })
            {
                currencies.forEach { currency ->
                    DropdownMenuItem(
                        text = { Text(text = currency) },
                        onClick = {
                            secondCurrency.value = currency
                            toCurrency.value = false
                        })
                }


            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {

            val rates=viewModel.rates
            if (rates!=null &&
                amount.value.isNotEmpty() &&
                firstCurrency.value.isNotEmpty()
                && secondCurrency.value.isNotEmpty())
            {
                val fromRate=rates.getRate(firstCurrency.value)
                val toRate=rates.getRate(secondCurrency.value)

                if (fromRate!=null && toRate!=null && fromRate!=toRate){
                    val amountValue=amount.value.toDouble()
                    val convertedAmount=(amountValue/fromRate)*toRate
                    val formattedAmount=String.format("%.2f",convertedAmount)
                    result.value="$amountValue ${firstCurrency.value}= $formattedAmount ${secondCurrency.value}"

                }else if (fromRate==toRate){
                    Toast.makeText(context,"You cant convert the same currency",Toast.LENGTH_SHORT).show()
                }
                else {
                    result.value="Invalid"
                }
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(70.dp)
            .height(60.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.placeholder2)
        ))

        {
            Text(text = "Convert", fontSize = 23.sp)

        }
        Spacer(modifier = Modifier.height(20.dp))
        
        Column {
            if (result.value.isNotEmpty()){
                Text(text = "Result")
                Text(text = result.value)
            }
        }
   
    }
}
