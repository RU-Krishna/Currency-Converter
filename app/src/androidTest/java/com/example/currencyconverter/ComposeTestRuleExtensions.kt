package com.example.currencyconverter

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

//AndroidComposeTestRule extensions for receiving String Resource...


/*
* Extension for onNodeWithTag method for receiving String resource directly from the user...
* */
fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A> , A>.onNodeWithTestTag(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithTag(activity.getString(id))


/*
* Extension for onNodeWithText method which receives a String resource...
* */
fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A> , A>.onNodeWithTextResource(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))

/*
* Extension for onNodeWithContentDescription receiving String resource directly as a parameter...
*
* */
fun <A: ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A> , A>.onNodeWithContentDesc(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithContentDescription(activity.getString(id))