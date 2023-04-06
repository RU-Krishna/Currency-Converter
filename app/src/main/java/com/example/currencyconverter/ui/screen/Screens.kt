package com.example.currencyconverter.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.currencyconverter.R


@Composable
fun LoadingScreen(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .testTag(
                    stringResource(id = R.string.progress_indicator_test_tag)
                ),
            strokeWidth = 4.dp,
            color = MaterialTheme.colorScheme.primary,
        )

    }

}

@Preview(showSystemUi = true)
@Composable
fun ErrorScreen(
    onRetry: () -> Unit = {}
) {

    AlertDialog(
        onDismissRequest = { onRetry() },
        confirmButton = { },
        dismissButton = {},
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.alert_icon),
                contentDescription = stringResource(
                    id = R.string.alert_icon_content_desc
                )
            )

        },
        title = {
            Text(
                text = stringResource(id = R.string.error_headline),
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.error_message),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .wrapContentWidth(Alignment.CenterHorizontally)

            )
        },
        iconContentColor = MaterialTheme.colorScheme.error,
        textContentColor = MaterialTheme.colorScheme.onErrorContainer,
        titleContentColor = MaterialTheme.colorScheme.onErrorContainer,
        containerColor = MaterialTheme.colorScheme.errorContainer,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .wrapContentSize()
            .testTag(stringResource(id = R.string.alert_dialog_test_tag))
    )


}