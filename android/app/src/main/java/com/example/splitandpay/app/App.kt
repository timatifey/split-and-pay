package com.example.splitandpay.app

import android.app.Application
import com.example.splitandpay.createproduct.di.createProductModule
import com.example.splitandpay.network.di.networkModule
import com.example.splitandpay.room.di.roomModule
import com.example.splitandpay.rooms.add.di.addRoomModule
import com.example.splitandpay.rooms.di.roomsListModule
import com.example.splitandpay.user.di.startUserModule
import com.example.splitandpay.user.di.userModule
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
                roomsListModule,
                networkModule,
                userModule,
                startUserModule,
                createProductModule,
                addRoomModule,
            )
        }
    }
}