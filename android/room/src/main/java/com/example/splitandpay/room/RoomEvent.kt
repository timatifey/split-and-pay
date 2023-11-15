package com.example.splitandpay.room

sealed interface RoomEvent {
    object onRetryClick : RoomEvent
    object onItemClick : RoomEvent
}