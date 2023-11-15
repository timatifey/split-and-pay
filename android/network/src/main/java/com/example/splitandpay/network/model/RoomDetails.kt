package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
public data class RoomDetails(

    @SerializedName("id")
    val id: Long,

    @SerializedName("owner")
    val owner: User,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("users")
    val users: List<User>,

    @SerializedName("receipt")
    val receipt: List<ReceiptItem>
)
