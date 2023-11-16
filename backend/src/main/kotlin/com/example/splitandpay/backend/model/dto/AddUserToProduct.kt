package com.example.splitandpay.backend.model.dto

import com.example.splitandpay.backend.serialization.ObjectIdSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class AddUserToProduct(
    val productId: Int,
    @Serializable(with = ObjectIdSerializer::class)
    val userId: ObjectId,
)
