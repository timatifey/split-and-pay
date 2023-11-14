package com.example.splitandpay.receipt.di

import com.example.splitandpay.receipt.ReceiptViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val receiptModule = module {
    viewModel {
        ReceiptViewModel()
    }
}