package com.example.currencyconverter.fake

import com.example.currencyconverter.model.Currency


//Fake Data for Currency class...
object FakeCurrencyDataSource {

    val currencyList = listOf(
        Currency(
            symbol = "USD",
            name = "United States Dollar"
        ),
        Currency(
            symbol = "ALL",
            name = "Albania Lek"
        ),
        Currency(
            symbol = "AMD",
            name = "Armenia Dram"
        ),
        Currency(
            symbol = "BTC",
            name = "Bitcoin"
        ),
        Currency(
            symbol = "CLP",
            name = "Chile Peso"
        ),
        Currency(
            symbol = "INR",
            name = "India Rupee"
        )
    )
}