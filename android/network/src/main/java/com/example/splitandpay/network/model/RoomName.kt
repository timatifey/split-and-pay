package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
public data class RoomName(

    @SerializedName("name")
    val roomName: String,
)
