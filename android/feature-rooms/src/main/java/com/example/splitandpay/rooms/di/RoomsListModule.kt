package com.example.splitandpay.rooms.di

import com.example.splitandpay.rooms.RoomsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val roomsListModule = module {
    viewModel {
        RoomsListViewModel(get(), get())
    }
}
