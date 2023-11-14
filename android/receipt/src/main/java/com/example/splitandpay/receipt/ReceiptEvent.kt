package com.example.splitandpay.receipt

sealed interface ReceiptEvent {
    object onRetryClick : ReceiptEvent
    // P.S. добавятся ещё ивенты
}