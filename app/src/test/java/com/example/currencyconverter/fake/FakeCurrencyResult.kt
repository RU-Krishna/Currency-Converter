package com.example.currencyconverter.fake

import com.example.currencyconverter.model.CurrencyResult
import com.example.currencyconverter.model.Result

//Fake CurrencyResult replaces the result obtained on calling getConverted Amount...
object FakeCurrencyResult {

    const val fromCurrency = "USD"
    const val toCurrency = "INR"
    const val amountToConvert = 1.0
    val currencyResult = CurrencyResult(
        success = true,
        validationMessage = listOf(" "),
        result = Result(
            from = fromCurrency,
            to = toCurrency,
            amountToConvert = amountToConvert,
            convertedAmount = 82.1945
        )
    )
}

