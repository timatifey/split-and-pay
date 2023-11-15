package com.example.splitandpay.receipt

internal sealed interface ReceiptState {

    data class Content(
        val items: List<String>,
    ) : ReceiptState

    data class Error(
        val text: String,
    ) : ReceiptState

    object Loading : ReceiptState
}