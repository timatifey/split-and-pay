package com.example.splitandpay.backend.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddProductFromCheckRequest(val checkData: String)
