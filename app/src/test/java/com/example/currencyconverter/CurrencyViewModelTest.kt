package com.example.currencyconverter

import com.example.currencyconverter.fake.FakeCurrencyDataSource
import com.example.currencyconverter.fake.FakeCurrencyRepository
import com.example.currencyconverter.fake.FakeCurrencyResult
import com.example.currencyconverter.rules.MainDispatchersRule
import com.example.currencyconverter.ui.screen.CurrencyViewModel
import com.example.currencyconverter.ui.screen.NetworkUIState
import com.example.currencyconverter.ui.screen.ScreenUIState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/*
* Testing Currency View Model methods...
* (Suspend Methods obtaining data over the network)
*
* */
@OptIn(ExperimentalCoroutinesApi::class)
class CurrencyViewModelTest {

    @get: Rule
    val testDispatcherRule = MainDispatchersRule()

    @Test
    fun currencyViewModel_getSupportedCurrencies_successUiState() {
        runTest {
            val viewModel = CurrencyViewModel(
                currencyRepository = FakeCurrencyRepository()
            )

            assertEquals(
                CurrencyViewModel.networkState, NetworkUIState.Available
            )

            viewModel.getSupportedCurrencies()

            advanceUntilIdle()

            assertEquals(
                CurrencyViewModel.networkState, NetworkUIState.Success
            )

            assertEquals(viewModel.supportedCurrencies, FakeCurrencyDataSource.currencyList)

        }
    }

    @Test
    fun currencyViewModel_getTheConvertedAmount_t082_1495() {

        runTest {

            val viewModel = CurrencyViewModel(
                currencyRepository = FakeCurrencyRepository()
            )

            viewModel.changeFromCurrency(FakeCurrencyResult.fromCurrency)
            viewModel.changeToCurrency(FakeCurrencyResult.toCurrency)
            viewModel.changeAmountToConvert(FakeCurrencyResult.amountToConvert.toString())
            viewModel.getTheConvertedAmount()

            advanceUntilIdle()

            assertEquals(
                ScreenUIState(
                    fromCurrency = "USD",
                    toCurrency = "INR",
                    amountToConvert = "1.0",
                    convertedAmount = "82.1945"

                ),
                viewModel.currencyScreenUiState.value

            )





        }
    }

}