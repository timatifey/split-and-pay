package com.example.splitandpay.rooms.add

internal sealed interface AddRoomEvent {

    data class OnCreateRoomClick(
        val roomName: String,
    ) : AddRoomEvent

    data class OnConnectRoomClick(
        val roomId: Long,
    ) : AddRoomEvent

    data class OnRoomIdFieldChange(
        val value: Long?
    ) : AddRoomEvent

    data class OnRoomNameFieldChange(
        val value: String
    ) : AddRoomEvent
}
