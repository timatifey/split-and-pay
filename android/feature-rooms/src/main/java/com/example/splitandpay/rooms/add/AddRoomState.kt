package com.example.splitandpay.rooms.add

internal sealed interface AddRoomState {

    data class Input(
        val roomName: String,
        val roomId: Long,
    ) : AddRoomState
}
