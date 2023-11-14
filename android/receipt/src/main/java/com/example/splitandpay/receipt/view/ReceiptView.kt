package com.example.splitandpay.receipt.view

import androidx.compose.runtime.Composable
import com.example.splitandpay.receipt.ReceiptEvent
import com.example.splitandpay.receipt.ReceiptState
import com.example.splitandpay.uikit.error.ErrorView
import com.example.splitandpay.uikit.loading.Loading

@Composable
internal fun ReceiptView(
    state: ReceiptState,
    onReceiptEvent: OnReceiptEvent,
) {
    when (state) {
        ReceiptState.Content -> Content()
        is ReceiptState.Error -> ErrorView(
            text = state.text,
            onClick = { onReceiptEvent(ReceiptEvent.onRetryClick) },
        )
        ReceiptState.Loading -> Loading()
    }
}

@Composable
private fun Content() {
    // TODO
}