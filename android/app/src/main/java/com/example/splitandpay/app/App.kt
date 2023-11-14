package com.example.splitandpay.app

import android.app.Application
import com.example.splitandpay.receipt.di.receiptModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                receiptModule,
            )
        }
    }
}