package com.example.splitandpay.room

import androidx.lifecycle.ViewModel
import com.example.splitandpay.room.models.ReceiptItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class RoomViewModel : ViewModel() {

    private val mockState = RoomState.Content(
        items = listOf(
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Мясо",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Хлеб",
                amount = 1.0,
                users = emptyList(),
            ),
            ReceiptItem(
                text = "Пиво",
                amount = 1.0,
                users = emptyList(),
            ),
        )
    )

    private val _state = MutableStateFlow(mockState)
    val state: StateFlow<RoomState>
        get() = _state

    fun onReceiptEvent(roomEvent: RoomEvent) {
        when (roomEvent) {
            RoomEvent.OnRetryClick -> onRetryClick()
            RoomEvent.OnItemClick -> onItemClick()
            RoomEvent.CreateReceiptItem -> onCreateReceiptItemClick()
        }
    }

    private fun onRetryClick() {
        // TODO
    }

    private fun onItemClick() {

    }

    private fun onCreateReceiptItemClick() {

    }
}