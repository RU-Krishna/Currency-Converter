package com.example.currencyconverter.data

import com.example.currencyconverter.network.CurrencyAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

//Dependency Injection...
interface Container {
    val currencyRepository: CurrencyRepository
}


class DefaultAppContainer: Container {

    private val BASE_URL = "https://currency-converter18.p.rapidapi.com/api/v1/"

    @OptIn(ExperimentalSerializationApi::class)
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    private val retrofitService: CurrencyAPI by lazy {
        retrofit.create(CurrencyAPI::class.java)
    }

    override val currencyRepository: CurrencyRepository by lazy {
        NetworkCurrencyRepository(retrofitService)
    }











}