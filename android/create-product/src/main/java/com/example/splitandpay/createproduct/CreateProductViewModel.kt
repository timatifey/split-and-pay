package com.example.splitandpay.createproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.splitandpay.createproduct.models.NewProduct
import com.example.splitandpay.network.SplitAndPayApiService
import com.example.splitandpay.network.model.Product
import com.example.splitandpay.user.UserDataHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class CreateProductViewModel(
    private val apiService: SplitAndPayApiService,
    private val userDataHolder: UserDataHolder,
) : ViewModel() {

    private var newProduct = NewProduct("", 1.0)

    private val _shouldDismiss = MutableLiveData(false)
    val shouldDismiss: LiveData<Boolean>
        get() = _shouldDismiss

    private val _state: MutableStateFlow<CreateProductState> =
        MutableStateFlow(CreateProductState.Input(newProduct))

    val state: StateFlow<CreateProductState>
        get() = _state

    private val userId: String
        get() = userDataHolder.userId!!

    fun onCreateProductEvent(createProductEvent: CreateProductEvent) {
        when (createProductEvent) {
            is CreateProductEvent.OnSubmitClick -> createProduct(createProductEvent.newProduct)
            is CreateProductEvent.OnProductNameFieldChange -> changeProductName(createProductEvent.value)
            is CreateProductEvent.OnProductAmountFieldChange -> changeProductAmount(createProductEvent.value)
        }
    }

    private fun changeProductName(productName: String) {
        newProduct = newProduct.copy(productName = productName)
        _state.value = CreateProductState.Input(newProduct)
    }

    private fun changeProductAmount(amount: Double?) {
        newProduct = newProduct.copy(amount = amount ?: 1.0)
        _state.value = CreateProductState.Input(newProduct)
    }

    private fun createProduct(newProduct: NewProduct) {
        viewModelScope.launch {
            apiService.addProduct(
                userId = userId,
                roomId = 1, //replace hardcode with args,
                product = Product(
                    productName = newProduct.productName,
                    amount = newProduct.amount,
                )
            )
        }

        _shouldDismiss.postValue(true)
    }
}