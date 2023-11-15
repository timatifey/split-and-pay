package com.example.splitandpay.backend.model.db

import com.example.splitandpay.backend.model.dto.OwnerDto
import com.example.splitandpay.backend.serialization.LocalDateTimeSerializer
import com.example.splitandpay.backend.serialization.ObjectIdSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import java.time.LocalDateTime

@Serializable
data class Room(
    @Id
    val id: Long,
    val name: String,
    val owner: OwnerDto,
    val participants: MutableList<@Serializable(with = ObjectIdSerializer::class) ObjectId> = mutableListOf(owner.id),
    val products: MutableMap<Product, MutableList<@Serializable(with = ObjectIdSerializer::class) ObjectId>> = mutableMapOf(),
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime
)
