package com.example.splitandpay.createproduct.di

import com.example.splitandpay.createproduct.CreateProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createProductModule = module {
    viewModel {
        CreateProductViewModel(get(), get())
    }
}