package com.example.splitandpay.backend.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddProductRequest(val name: String, val amount: Double)
