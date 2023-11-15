package com.example.splitandpay.receipt

sealed interface ReceiptEvent {
    object onRetryClick : ReceiptEvent
    object onItemClick : ReceiptEvent
}