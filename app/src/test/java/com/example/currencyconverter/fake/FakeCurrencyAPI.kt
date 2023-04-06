package com.example.currencyconverter.fake

import com.example.currencyconverter.model.Currency
import com.example.currencyconverter.model.CurrencyResult
import com.example.currencyconverter.network.CurrencyAPI

//FakeCurrencyAPI class implementing CurrencyAPI interface of network package...
class FakeCurrencyAPI: CurrencyAPI{
    override suspend fun getSupportedCurrencies(): List<Currency> {
      return FakeCurrencyDataSource.currencyList
    }

    override suspend fun getConvertedCurrency(
        from: String,
        to: String,
        amount: Double
    ): CurrencyResult {
        return FakeCurrencyResult.currencyResult
    }

}