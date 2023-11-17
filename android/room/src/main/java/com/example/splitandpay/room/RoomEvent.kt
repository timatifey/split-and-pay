package com.example.splitandpay.room

import com.example.splitandpay.room.models.ReceiptItem

internal sealed interface RoomEvent {
    object OnRetryClick : RoomEvent
    data class OnItemClick(
        val receiptItem: ReceiptItem,
    ) : RoomEvent
    object CreateReceiptItem: RoomEvent
    data class OnQrCodeScan(val data: String) : RoomEvent
}
