package com.example.splitandpay.user.di

import com.example.splitandpay.user.start.StartUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startUserModule = module {
    viewModel {
        StartUserViewModel(get(), get())
    }
}
