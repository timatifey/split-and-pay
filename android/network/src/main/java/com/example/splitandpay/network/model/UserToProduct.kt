package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
public data class UserToProduct(

    @SerializedName("productId")
    val productId: Long,

    @SerializedName("userId")
    val userId: String,
)
