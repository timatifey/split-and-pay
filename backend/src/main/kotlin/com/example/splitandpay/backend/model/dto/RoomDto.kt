package com.example.splitandpay.backend.model.dto

import com.example.splitandpay.backend.serialization.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class RoomDto(
    val id: Long,
    val name: String,
    val owner: OwnerDto,
    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    val users: List<OwnerDto>? = null,
    val receipt: List<ProductDto>? = null,
    val totalSum: Double? = null,
)
