package com.example.splitandpay.backend.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class SumPerProductDto(
    val id: Int,
    val name: String,
    val sum: Double
)
