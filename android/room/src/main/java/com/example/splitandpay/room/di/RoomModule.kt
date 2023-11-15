package com.example.splitandpay.room.di

import com.example.splitandpay.room.RoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomModule = module {
    viewModel {
        RoomViewModel()
    }
}