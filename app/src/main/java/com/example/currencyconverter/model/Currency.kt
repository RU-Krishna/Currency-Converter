package com.example.currencyconverter.model


/*
*  JSon Currency objects obtained from the API...
*  deserialize into Kotlin using Kotlin Serializable plugin...
*
* */
@kotlinx.serialization.Serializable
data class Currency(
    val symbol: String,
    val name: String
)
