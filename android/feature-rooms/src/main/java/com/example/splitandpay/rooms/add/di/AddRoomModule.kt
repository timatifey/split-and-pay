package com.example.splitandpay.rooms.add.di

import com.example.splitandpay.rooms.add.AddRoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addRoomModule = module {
    viewModel {
        AddRoomViewModel(get(), get())
    }
}
