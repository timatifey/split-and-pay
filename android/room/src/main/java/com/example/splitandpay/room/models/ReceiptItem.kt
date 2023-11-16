package com.example.splitandpay.room.models

internal data class ReceiptItem(
    val id: Long = -1,
    val text: String,
    val amount: Double,
    val mainUser: User?,
    val users: List<User>,
)
