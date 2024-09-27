package com.payclip.blaze.emc.embedded_connection_sdk.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.payclip.blaze.emc.embedded_connection_sdk.ui.theme.EmbeddedconnectionsdkTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    val viewModel: HomeViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state) {
        when (val value = state) {
            is HomeScreenState.PrintingError -> {
                snackBarHostState.showSnackbar("Error printing: ${value.error}")
            }

            is HomeScreenState.PrintingSuccess -> {
                snackBarHostState.showSnackbar("Printing success")
            }

            is HomeScreenState.Printing -> {
                snackBarHostState.showSnackbar("Printing")
            }

            else -> {}
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }) { innerPadding ->


        if (scrollState.canScrollBackward) {
            HorizontalDivider()
        }

        FlowRow(
            modifier =
            Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(innerPadding),
            horizontalArrangement = Arrangement.Center
        ) {
            Greeting(
                name = "CLIP PRINTER ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
            )
            if (state is HomeScreenState.Printing) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 24.dp))
            } else {
                PrinterButton(
                    modifier =
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .padding(top = 24.dp)
                        .height(56.dp)
                        .fillMaxWidth(),
                    viewModel = viewModel
                )
            }
        }

        if (scrollState.canScrollForward) {
            HorizontalDivider()
        }
    }
}

@Composable
fun PrinterButton(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val context = LocalContext.current

    Button(
        modifier = modifier,
        onClick = {
            viewModel.print(context)
        },
    ) {
        Text(text = "Print")
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Embedded Connection SDK",
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EmbeddedconnectionsdkTheme {
        HomeScreen()
    }
}