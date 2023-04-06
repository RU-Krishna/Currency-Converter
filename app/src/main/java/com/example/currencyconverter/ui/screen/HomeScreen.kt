package com.example.currencyconverter.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.model.Currency


@Composable
fun HomeScreen(
    viewModel: CurrencyViewModel = viewModel(factory = CurrencyViewModel.factory),
) {

    var bottomSheetState by remember {
        mutableStateOf(false)
    }


    var from by remember {
        mutableStateOf(false)
    }

    val networkState = CurrencyViewModel.networkState


    Scaffold(
        topBar = {
            CurrencyTopBar()
        }
    ) { innerPadding ->

        when(networkState) {
            is NetworkUIState.Loading -> {
                LoadingScreen(modifier = Modifier.padding(innerPadding))
            }
            is NetworkUIState.Available, NetworkUIState.Success -> {
                if(viewModel.supportedCurrencies.isEmpty())
                  viewModel.getSupportedCurrencies()


                CurrencyExchangeScreen(
                    viewModel = viewModel,
                    openBottomSheet = { bottomSheetState = true },
                    selectedFrom = { from = true },
                    selectedTo = { from = false },
                    modifier = Modifier.padding(innerPadding)
                )
                CurrencyBottomSheet(
                    bottomSheetState,
                    onDismiss = { bottomSheetState = false },
                    onClick = { currency ->
                        if (from) {
                            viewModel.changeFromCurrency(currency)
                            viewModel.getTheConvertedAmount()
                        } else {
                            viewModel.changeToCurrency(currency)
                            viewModel.getTheConvertedAmount()
                        }
                    },
                    supportedCurrencies = viewModel.supportedCurrencies
                )

            }
            is NetworkUIState.Unavailable -> {
                ErrorScreen(
                    onRetry = {
                        viewModel.getTheConvertedAmount()
                    }
                )
            }
        }


}


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyTopBar() {
    TopAppBar(
        title = {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.currency_app_bar),
                    contentDescription = "Currency",
                    modifier = Modifier
                        .padding(start = 4.dp, end =4.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.headlineLarge
                )

            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .testTag(stringResource(id = R.string.app_bar_test_tag))
    )
}

@Composable
fun CurrencyExchangeScreen(
    viewModel: CurrencyViewModel,
    openBottomSheet: () -> Unit,
    selectedFrom: () -> Unit,
    selectedTo: () -> Unit,
    modifier: Modifier = Modifier
) {


    val uiState = viewModel.currencyScreenUiState.collectAsState()



    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = {
                openBottomSheet()
                selectedFrom()
                },
                modifier = Modifier.testTag(
                    stringResource(id = R.string.currency_exchange_button_from )
                )
            ) {
                Text(
                    text = uiState.value.fromCurrency,
                    style = MaterialTheme.typography.headlineMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_drop_down),
                    contentDescription = stringResource(
                        id = R.string.from_button_drop_down
                    )
                )
            }
            OutlinedTextField(
                value = uiState.value.amountToConvert,
                onValueChange = {
                    viewModel.changeAmountToConvert(it)
                    viewModel.getTheConvertedAmount()
                },
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                keyboardOptions = KeyboardOptions.Default
                    .copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                label = {
                    Text(
                        text = "Amount",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                textStyle = MaterialTheme.typography.headlineSmall
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(
                onClick = {
                openBottomSheet()
                selectedTo()
            },
                modifier = Modifier
                    .testTag(
                        stringResource(
                            id = R.string.currency_exchange_button_to
                        )
                    )
            ) {
                Text(
                    text = uiState.value.toCurrency,
                    style = MaterialTheme.typography.headlineMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_drop_down),
                    contentDescription = stringResource(
                        id = R.string.to_button_drop_down
                    )
                )
            }
            Text(
                text = uiState.value.convertedAmount,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface
            )
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyBottomSheet(
    openSheetState: Boolean = false,
    onDismiss: () -> Unit,
    onClick: (String) -> Unit,
    supportedCurrencies: List<Currency>
) {

    AnimatedVisibility(
        visible = openSheetState
    ) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            shape = BottomSheetDefaults.MinimizedShape,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            modifier = Modifier
                .testTag(
                    stringResource(id = R.string.bottom_sheet_test_tag)
                )
        ) {
            LazyColumn {
                items(items = supportedCurrencies, key = { item: Currency -> item.symbol }) {
                    TextButton(
                        onClick = {
                            onClick(it.symbol)
                            onDismiss()

                        }
                    ) {
                        Text(
                            "${it.name} (${it.symbol})",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(4.dp)
                        )

                    }
                }

            }

        }
    }
}


