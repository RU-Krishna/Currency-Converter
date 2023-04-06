package com.example.currencyconverter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun CurrencyConverterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        darkTheme -> darkColorPalette
        else -> lightColorPalette
    }
    MaterialTheme(
        typography = currencyTypography,
        colorScheme = colorScheme,
        content = content
    )

}




