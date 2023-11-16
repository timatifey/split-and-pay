package com.example.splitandpay.room.di

import com.example.splitandpay.room.RoomModelMapper
import com.example.splitandpay.room.RoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomModule = module {
    single { RoomModelMapper() }
    viewModel {
        RoomViewModel(get(), get(), get())
    }
}