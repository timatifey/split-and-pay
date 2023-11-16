package com.example.splitandpay.createproduct

import com.example.splitandpay.createproduct.models.NewProduct

internal sealed interface CreateProductEvent {
    data class OnSubmitClick(
        val newProduct: NewProduct
    ) : CreateProductEvent

    data class OnProductNameFieldChange(
        val value: String
    ) : CreateProductEvent

    data class OnProductAmountFieldChange(
        val value: Double?
    ) : CreateProductEvent
}