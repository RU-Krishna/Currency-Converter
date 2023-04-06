package com.example.currencyconverter.data

import com.example.currencyconverter.model.Currency
import com.example.currencyconverter.model.CurrencyResult
import com.example.currencyconverter.network.CurrencyAPI


//Dependency Injection for NetworkRepository class.
interface CurrencyRepository {

    suspend fun getSupportedCurrencies(): List<Currency>

    suspend fun getConvertedCurrency(from: String, to: String, amount: Double): CurrencyResult

}


class NetworkCurrencyRepository(
    private val currencyAPI: CurrencyAPI
): CurrencyRepository {

    override suspend fun getSupportedCurrencies(): List<Currency> =
        currencyAPI.getSupportedCurrencies()

    override suspend fun getConvertedCurrency(from: String, to: String, amount: Double): CurrencyResult =
        currencyAPI.getConvertedCurrency(from, to, amount)

}