package com.example.splitandpay.backend.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val name: String,
    val id: Int,
    val amount: Double,
    val users: List<OwnerDto>
)
