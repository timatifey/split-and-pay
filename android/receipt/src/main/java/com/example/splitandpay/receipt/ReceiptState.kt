package com.example.splitandpay.receipt

internal sealed interface ReceiptState {

    object Content : ReceiptState

    data class Error(
        val text: String,
    ) : ReceiptState

    object Loading : ReceiptState
}