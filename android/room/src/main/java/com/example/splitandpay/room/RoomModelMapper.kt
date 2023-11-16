package com.example.splitandpay.room

import com.example.splitandpay.room.models.ReceiptItem
import com.example.splitandpay.room.models.User
import com.example.splitandpay.network.model.User as UserDTO
import com.example.splitandpay.network.model.ReceiptItem as ReceiptItemDTO


internal class RoomModelMapper {

    fun mapReceiptItem(receiptItem: ReceiptItemDTO) : ReceiptItem =
        ReceiptItem(
            text = receiptItem.name,
            amount = receiptItem.amount,
            users = receiptItem.users.map(::mapUser)
        )

    private fun mapUser(user: UserDTO): User =
        User(
            id = user.id,
            username = user.username,
            shortName = user.shortName,
        )
}