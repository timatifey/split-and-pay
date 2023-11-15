package com.example.splitandpay.backend.service.check.dto

import kotlinx.serialization.Serializable

@Serializable
data class CheckDto(
    val data: Data
)

@Serializable
data class Data(
    val json: Json
)

@Serializable
data class Json(
    val items: List<Item>
)

@Serializable
data class Item(
    val name: String,
    val sum: Int
)