package com.example.currencyconverter

import com.example.currencyconverter.data.NetworkCurrencyRepository
import com.example.currencyconverter.fake.FakeCurrencyAPI
import com.example.currencyconverter.fake.FakeCurrencyDataSource
import com.example.currencyconverter.fake.FakeCurrencyResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/*
* Testing NetworkCurrencyRepository Methods...
*
* */


@OptIn(ExperimentalCoroutinesApi::class)

class CurrencyRepositoryTest {


    @Test
    fun networkCurrencyRepository_getSupportedCurrencies_verifySupportedCurrencies() {

        runTest {
            val repository = NetworkCurrencyRepository(
                currencyAPI = FakeCurrencyAPI()
            )

            assertEquals(
                FakeCurrencyDataSource.currencyList,
                repository.getSupportedCurrencies()
            )
        }
    }

    @Test
    fun networkCurrencyRepository_getConvertedAmount_verifyCurrencyResult() {
        runTest {
            val repository = NetworkCurrencyRepository(
                currencyAPI = FakeCurrencyAPI()
            )

            assertEquals(
                FakeCurrencyResult.currencyResult,
                repository.getConvertedCurrency(
                    from = FakeCurrencyResult.fromCurrency,
                    to = FakeCurrencyResult.toCurrency,
                    amount = FakeCurrencyResult.amountToConvert
                )
            )
        }
    }

}