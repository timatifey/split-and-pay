package com.example.splitandpay.rooms.add

internal sealed interface AddRoomEvent {

    object OnCreateRoomClick : AddRoomEvent

    object OnConnectRoomClick : AddRoomEvent

    data class OnRoomIdFieldChange(
        val value: String,
    ) : AddRoomEvent

    data class OnRoomNameFieldChange(
        val value: String,
    ) : AddRoomEvent
}
