package com.example.splitandpay.backend.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val username: String
)
