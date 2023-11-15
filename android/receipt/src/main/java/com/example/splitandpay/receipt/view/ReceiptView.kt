package com.example.splitandpay.receipt.view

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.splitandpay.receipt.ReceiptEvent
import com.example.splitandpay.receipt.ReceiptState
import com.example.splitandpay.uikit.error.ErrorView
import com.example.splitandpay.uikit.loading.Loading
import com.example.splitandpay.uikit.theme.MyApplicationTheme

@Composable
internal fun ReceiptView(
    state: ReceiptState,
    onReceiptEvent: OnReceiptEvent,
) {
    when (state) {
        is ReceiptState.Content -> Content(
            state = state,
            onReceiptEvent = onReceiptEvent,
        )
        is ReceiptState.Error -> ErrorView(
            text = state.text,
            onClick = { onReceiptEvent(ReceiptEvent.onRetryClick) },
        )
        ReceiptState.Loading -> Loading()
    }
}

@Composable
private fun Content(
    state: ReceiptState.Content,
    onReceiptEvent: OnReceiptEvent,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 100.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(state.items) {
            ReceiptItem(
                text = it,
                onClick = { onReceiptEvent(ReceiptEvent.onItemClick) }
            )
        }
    }

}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Night Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ReceiptViewPreview() {
    MyApplicationTheme {
        ReceiptView(
            state = ReceiptState.Content(
                items = listOf(
                    "Мясо",
                    "Хлеб",
                    "Пиво"
                )
            ),
            onReceiptEvent = {},
        )
    }
}