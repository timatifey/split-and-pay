package com.example.splitandpay.receipt

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class ReceiptViewModel : ViewModel() {

    private val _state = MutableStateFlow(ReceiptState.Loading)
    val state: StateFlow<ReceiptState>
        get() = _state

    fun onReceiptEvent(receiptEvent: ReceiptEvent) {
        when (receiptEvent) {
            ReceiptEvent.onRetryClick -> onRetryClick()
        }
    }

    private fun onRetryClick() {
        // TODO
    }
}