package com.example.currencyconverter.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.currencyconverter.CurrencyApplication
import com.example.currencyconverter.data.CurrencyRepository
import com.example.currencyconverter.model.Currency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val TAG = "com.example.CurrencyConverter.Exception"

//Network State...
sealed interface NetworkUIState {

    object Available: NetworkUIState

    object Unavailable: NetworkUIState

    object Loading: NetworkUIState

    object Success: NetworkUIState
}


//View Model for the application...
class CurrencyViewModel(private val currencyRepository: CurrencyRepository): ViewModel() {



    private val _currencyScreenUiState = MutableStateFlow(ScreenUIState())
    val currencyScreenUiState = _currencyScreenUiState.asStateFlow()

    var supportedCurrencies: List<Currency> by  mutableStateOf(emptyList())
    private set

    init {
         getSupportedCurrencies()
    }


      fun getSupportedCurrencies() {
            viewModelScope.launch {
                try {
                    networkState = NetworkUIState.Loading
                    supportedCurrencies = currencyRepository.getSupportedCurrencies()
                    networkState = NetworkUIState.Success
                }
                catch(e: Exception){
                    networkLost()
                    Log.d(TAG,"Exception:- $e")
                }
            }
    }

    fun changeFromCurrency(fromCurrency: String) {
        _currencyScreenUiState.update {
            it.copy(fromCurrency = fromCurrency)
        }
    }

    fun changeToCurrency(toCurrency: String) {
        _currencyScreenUiState.update {
            it.copy(toCurrency = toCurrency)
        }
    }

    fun changeAmountToConvert(amountToConvert: String) {
        _currencyScreenUiState.update {
            it.copy(amountToConvert = amountToConvert)
        }
    }

    fun getTheConvertedAmount() {
        viewModelScope.launch {
            try {
                _currencyScreenUiState.update {
                    it.copy(
                        convertedAmount =
                        currencyRepository.getConvertedCurrency(
                            from = it.fromCurrency,
                            to = it.toCurrency,
                            amount = if (it.amountToConvert.isEmpty()) 0.0 else it.amountToConvert.toDouble()
                        ).result.convertedAmount.toString()
                    )
                }
            }
            catch(e: Exception) {
             networkLost()
                Log.d(TAG,"Exception:- $e")
            }
        }
    }




    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CurrencyApplication)
                val currencyRepository = application.container.currencyRepository
                CurrencyViewModel(currencyRepository)
            }
        }

        var networkState: NetworkUIState by mutableStateOf(NetworkUIState.Available)
            private set

        fun networkLost() {
            networkState = NetworkUIState.Unavailable
        }

        fun networkAvailable() {
            networkState = NetworkUIState.Available
        }
    }


}

//Screen UI State...
data class ScreenUIState(
    val fromCurrency: String = "EUR",
    val toCurrency: String = "INR",
    val amountToConvert: String = "",
    val convertedAmount: String = "0.0"
)
