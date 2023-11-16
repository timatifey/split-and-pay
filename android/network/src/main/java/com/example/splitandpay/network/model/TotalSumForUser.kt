package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TotalSumForUser(
    @SerializedName("userId")
    val userId: String,

    @SerializedName("total")
    val total: Double,

    @SerializedName("perProduct")
    val perProduct: List<PerProduct>,
)