package com.example.splitandpay.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val name: String,
    val cost: Double,
    val currency: String
)
