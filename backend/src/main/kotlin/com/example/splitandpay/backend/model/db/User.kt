package com.example.splitandpay.backend.model.db

import com.example.splitandpay.backend.serialization.ObjectIdSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

@Serializable
data class User(
    @Id
    @Serializable(with = ObjectIdSerializer::class)
    val id: ObjectId = ObjectId.get(),
    var name: String,
    val rooms: MutableList<Long> = mutableListOf()
)
