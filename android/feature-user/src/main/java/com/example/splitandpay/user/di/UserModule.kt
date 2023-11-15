package com.example.splitandpay.user.di

import com.example.splitandpay.user.UserDataHolder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val userModule = module {
    single { UserDataHolder(androidContext()) }
}
