package com.example.splitandpay.receipt

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class ReceiptViewModel : ViewModel() {

    private val mockState = ReceiptState.Content(
        items = listOf(
            "Мясо",
            "Хлеб",
            "Пиво",
            "Мясо",
            "Хлеб",
            "Пиво",
            "Мясо",
            "Хлеб",
            "Пиво",
            "Мясо",
            "Хлеб",
            "Пиво",
            "Мясо",
            "Хлеб",
            "Пиво",
            "Мясо",
            "Хлеб",
            "Пиво",
        )
    )

    private val _state = MutableStateFlow(mockState)
    val state: StateFlow<ReceiptState>
        get() = _state

    fun onReceiptEvent(receiptEvent: ReceiptEvent) {
        when (receiptEvent) {
            ReceiptEvent.onRetryClick -> onRetryClick()
            ReceiptEvent.onItemClick -> onItemClick()
        }
    }

    private fun onRetryClick() {
        // TODO
    }

    private fun onItemClick() {

    }
}