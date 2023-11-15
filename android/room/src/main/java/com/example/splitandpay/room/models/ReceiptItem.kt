package com.example.splitandpay.room.models

data class ReceiptItem(
    val text: String,
    val amount: Double,
    val users: List<User>,
)
