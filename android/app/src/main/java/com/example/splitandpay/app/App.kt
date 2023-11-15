package com.example.splitandpay.app

import android.app.Application
import com.example.splitandpay.network.di.networkModule
import com.example.splitandpay.room.di.roomModule
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
                roomModule,
                networkModule,
            )
        }
    }
}