package com.example.splitandpay.createproduct

import com.example.splitandpay.createproduct.models.NewProduct

internal sealed interface CreateProductState {

    data class Input(
        val newProduct: NewProduct,
    ) : CreateProductState
}