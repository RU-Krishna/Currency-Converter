package com.example.currencyconverter

import android.app.Application
import com.example.currencyconverter.data.Container
import com.example.currencyconverter.data.DefaultAppContainer

class CurrencyApplication: Application() {

    lateinit var container: Container

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}