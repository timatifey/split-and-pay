package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
public data class RoomId(

    @SerializedName("id")
    val id: Long,
)
