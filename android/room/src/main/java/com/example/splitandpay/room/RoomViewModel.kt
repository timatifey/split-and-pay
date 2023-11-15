package com.example.splitandpay.room

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class RoomViewModel : ViewModel() {

    private val _state = MutableStateFlow(RoomState.Loading)
    val state: StateFlow<RoomState>
        get() = _state

    fun onReceiptEvent(roomEvent: RoomEvent) {
        when (roomEvent) {
            RoomEvent.onRetryClick -> onRetryClick()
            RoomEvent.onItemClick -> onItemClick()
        }
    }

    private fun onRetryClick() {
        // TODO
    }

    private fun onItemClick() {

    }
}