package com.example.currencyconverter

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.currencyconverter.ui.screen.HomeScreen
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/*
*
* Class involving UI test for all the screen cases...
*
* */
class CurrencyConverterUITest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun currencyConverter_UI_Setup() {
        composeTestRule
            .setContent {
                CurrencyConverterTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background

                    ) {

                        HomeScreen()

                    }

                }
            }

    }

    //App Bar Test Cases...
    @Test
    fun currencyTopAppBar_currencyIcon_Success() {
        composeTestRule.onNodeWithContentDescription("Currency")
    }

    @Test
    fun currencyTopAppBar_AppBar_Success() {
        composeTestRule
            .onNodeWithTestTag(R.string.app_bar_test_tag)

    }

    @Test
    fun currencyTopAppBar_appNameCurrencyConverter_Success() {
        composeTestRule
            .onNodeWithTextResource(R.string.app_name)
    }


    //Loading Screen Test Case...
    @Test
    fun loadingScreen_progressIndicator_Success() {
        composeTestRule
            .onNodeWithTestTag(R.string.progress_indicator_test_tag)
    }

    //Error Screen Test Case Due to Network Error...
    @Test
    fun errorScreen_alertDialog_Present() {
        composeTestRule
            .onNodeWithTestTag(R.string.alert_dialog_test_tag)
    }

    @Test
    fun errorScreen_alertIcon_Present() {
        composeTestRule
            .onNodeWithContentDesc(R.string.alert_icon_content_desc)
    }


    //CurrencyBottomSheet Testing...
    @Test
    fun currencyBottomSheet_modalBottomSheet_Present() {

        loading()

        composeTestRule
            .onNodeWithContentDesc(R.string.from_button_drop_down)
            .performClick()

        composeTestRule
            .onNodeWithTestTag(
                R.string.bottom_sheet_test_tag
            )
    }

    //CurrencyExchangeScreen Testing...
    @Test
    fun currencyExchangeScreen_TextButtonHasClickAction_true() {


        loading()

        composeTestRule
            .onNodeWithTestTag(
                R.string.currency_exchange_button_from
            )
            .assertHasClickAction()

        composeTestRule
            .onNodeWithTestTag(
                R.string.currency_exchange_button_to
            )
            .assertHasClickAction()

    }

    @Test
    fun currencyExchangeScreen_changingFromButtonCurrency_currencyTextChangedToUSD() {

        loading()

        composeTestRule
            .onNodeWithTestTag(
                R.string.currency_exchange_button_from
            )
            .performClick()

        composeTestRule
            .onNodeWithTestTag(
                R.string.bottom_sheet_test_tag
            )
            .performClick()

        composeTestRule
            .onNodeWithText("USD", substring = true)
            .performClick()

        composeTestRule
            .onNodeWithText("USD", substring = true)
            .assertExists("USD not Present")

    }


}

fun loading() {
    runBlocking {
        delay(5000)
    }
}