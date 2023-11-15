package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
public data class Product(

    @SerializedName("name")
    val productName: String,

    @SerializedName("amount")
    val amount: Double,
)
