package com.example.currencyconverter.model


/*
*
* JSON result obtained on the request of converting amount...
* deserialize into Kotlin objects...
*
* */
@kotlinx.serialization.Serializable
data class CurrencyResult(
    val success: Boolean,
    val validationMessage: List<String>,
    val result: Result
)
@kotlinx.serialization.Serializable
data class Result(
    val from: String,
    val to: String,
    val amountToConvert:Double,
    val convertedAmount: Double
)
