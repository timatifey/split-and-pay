package com.example.splitandpay.room

import com.example.splitandpay.room.models.ReceiptItem
import com.example.splitandpay.room.models.User
import com.example.splitandpay.network.model.User as UserDTO
import com.example.splitandpay.network.model.ReceiptItem as ReceiptItemDTO


internal class RoomModelMapper {

    fun mapReceiptItem(mainUser: String, receiptItem: ReceiptItemDTO) : ReceiptItem {
        val users = receiptItem.users.map(::mapUser)

        return ReceiptItem(
            id = receiptItem.id,
            text = receiptItem.name,
            amount = receiptItem.amount,
            mainUser = users.firstOrNull { it.id == mainUser },
            users = users.filterNot { it.id == mainUser },
        )
    }

    private fun mapUser(user: UserDTO): User =
        User(
            id = user.id,
            username = user.username,
            shortName = user.shortName,
        )
}
