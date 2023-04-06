package com.example.currencyconverter.fake

import com.example.currencyconverter.data.CurrencyRepository
import com.example.currencyconverter.model.Currency
import com.example.currencyconverter.model.CurrencyResult

//FakeCurrencyRepository class implementing CurrencyRepository interface...
class FakeCurrencyRepository: CurrencyRepository {
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