package com.example.splitandpay.receipt

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReceiptViewModel : ViewModel() {

    private val _state = MutableStateFlow("Temporary")
    val state: StateFlow<Any>
        get() = _state
}