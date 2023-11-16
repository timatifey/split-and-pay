package com.example.splitandpay.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
public data class CheckData(

    /**
     * @sample "fn=9280440300770583&fd=33110&fp=4138469556&n=1&t=20210217T2028&qr=0"
     */
    @SerializedName("checkData")
    val checkData: String,
)
