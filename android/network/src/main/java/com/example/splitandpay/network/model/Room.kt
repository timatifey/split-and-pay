package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
public data class Room(

    @SerializedName("id")
    val id: Long,

    @SerializedName("owner")
    val owner: User,

    @SerializedName("createdAt")
    val createdAt: String,
)

