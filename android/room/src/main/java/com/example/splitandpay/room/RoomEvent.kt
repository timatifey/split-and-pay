package com.example.splitandpay.room

sealed interface RoomEvent {
    object OnRetryClick : RoomEvent
    object OnItemClick : RoomEvent
    object CreateReceiptItem: RoomEvent
}