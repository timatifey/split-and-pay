package com.example.splitandpay.rooms

import com.example.splitandpay.rooms.model.RoomsListItem

internal sealed interface RoomsListState {

    data class Content(
        val items: List<RoomsListItem>,
    ) : RoomsListState

    data class Error(
        val text: String,
    ) : RoomsListState

    object Loading : RoomsListState

    object EmptyList : RoomsListState
}
