package com.example.currencyconverter

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.ui.screen.CurrencyViewModel
import com.example.currencyconverter.ui.screen.HomeScreen
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {


    private lateinit var connectivityManager: ConnectivityManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.
                    fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {

                    HomeScreen()

                }

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onResume() {
        super.onResume()
        Log.d("NetworkState", "On Resume Called")

        connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)
            .build()




        connectivityManager.registerNetworkCallback(networkRequest, NetworkCallback)
    }

    override fun onPause() {
        super.onPause()

        Log.d("NetworkState", "On Stop Called")
        connectivityManager.unregisterNetworkCallback(
            NetworkCallback
        )

    }

    object NetworkCallback: ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            Log.d("NetworkState"," Network Available $network")
            CurrencyViewModel.networkAvailable()

        }


        override fun onLost(network: Network) {
            super.onLost(network)
            Log.d("NetworkState", "Network Lost $network")
            CurrencyViewModel.networkLost()
        }
    }
}

