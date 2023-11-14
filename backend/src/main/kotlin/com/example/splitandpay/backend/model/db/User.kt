package com.example.splitandpay.backend.model.db

import com.example.splitandpay.backend.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import org.springframework.data.annotation.Id
import java.util.UUID

@Serializable
data class User(
    @Id
    @Serializable(with = UUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    var name: String?,
    val rooms: MutableList<@Serializable(with = UUIDSerializer::class) UUID> = mutableListOf()
)
