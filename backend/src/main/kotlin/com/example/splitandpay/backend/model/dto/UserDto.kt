package com.example.splitandpay.backend.model.dto

import com.example.splitandpay.backend.serialization.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserDto(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID
)
