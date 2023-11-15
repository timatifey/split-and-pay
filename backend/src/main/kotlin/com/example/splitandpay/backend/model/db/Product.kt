package com.example.splitandpay.backend.model.db

import com.example.splitandpay.backend.serialization.ObjectIdSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class Product(
    val name: String,
    val amount: Double,
    val users: MutableList<@Serializable(with = ObjectIdSerializer::class) ObjectId> = mutableListOf()
)
