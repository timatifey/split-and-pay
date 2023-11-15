package com.example.splitandpay.room

import com.example.splitandpay.room.models.ReceiptItem

internal sealed interface RoomState {

    data class Content(
        val items: List<ReceiptItem>,
    ) : RoomState

    data class Error(
        val text: String,
    ) : RoomState

    object Loading : RoomState
}