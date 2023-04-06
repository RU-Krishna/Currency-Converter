package com.example.currencyconverter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R


val alkatraFontFamily = FontFamily(
    Font(R.font.alkatra_regular),
    Font(R.font.alkatra_semibold),
)

val currencyTypography = Typography(

    headlineLarge = TextStyle(
        fontFamily = alkatraFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 40.sp

    ),
    headlineMedium = TextStyle(
        fontFamily = alkatraFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = alkatraFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    labelMedium = TextStyle(
        fontFamily = alkatraFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp

    ),
    labelLarge = TextStyle(
        fontFamily = alkatraFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp

    ),
    titleLarge = TextStyle(
        fontFamily = alkatraFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 24.sp

    ),
    bodyLarge = TextStyle(
        fontFamily = alkatraFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
)




