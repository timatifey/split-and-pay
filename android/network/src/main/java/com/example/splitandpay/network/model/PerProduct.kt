package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PerProduct(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("sum")
    val sum: Double
)
