package com.example.splitandpay.backend.model.dto

import com.example.splitandpay.backend.serialization.ObjectIdSerializer
import com.example.splitandpay.backend.utils.getShortName
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class OwnerDto(
    @Serializable(with = ObjectIdSerializer::class)
    val id: ObjectId,
    val username: String,
    val shortName: String,
) {
    constructor(id: ObjectId, username: String) : this(id, username, username.getShortName())
}
