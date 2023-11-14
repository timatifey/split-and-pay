package com.example.splitandpay.backend.model

import com.example.splitandpay.backend.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import org.springframework.data.annotation.Id
import java.util.UUID

@Serializable
data class Room(
    @Id
    @Serializable(with = UUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    val participants: MutableList<@Serializable(with = UUIDSerializer::class) UUID>,
    val products: MutableMap<Product, MutableList<@Serializable(with = UUIDSerializer::class) UUID>> = mutableMapOf()
)
