package com.example.currencyconverter.network

import com.example.currencyconverter.X_RapidAPI_Host
import com.example.currencyconverter.X_RapidAPI_Key
import com.example.currencyconverter.model.Currency
import com.example.currencyconverter.model.CurrencyResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


/*
*
* Get Requests for APIs
*
* */
interface CurrencyAPI {

    @Headers(
        "X-RapidAPI-Key: $X_RapidAPI_Key",
        "X-RapidAPI-Host: $X_RapidAPI_Host"
    )
    @GET("supportedCurrencies")
    suspend fun getSupportedCurrencies(): List<Currency>

    @Headers(
        "X-RapidAPI-Key: $X_RapidAPI_Key",
        "X-RapidAPI-Host: $X_RapidAPI_Host"
    )
    @GET("convert")
    suspend fun getConvertedCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): CurrencyResult

}
