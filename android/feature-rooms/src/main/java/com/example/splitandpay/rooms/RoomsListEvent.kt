package com.example.splitandpay.rooms

internal sealed interface RoomsListEvent {

    data class OnRoomsItemClick(val roomId: Long) : RoomsListEvent

    object OnAddButtonClick : RoomsListEvent

    object OnRetryClick : RoomsListEvent

    object OnPullToRefresh : RoomsListEvent
}
