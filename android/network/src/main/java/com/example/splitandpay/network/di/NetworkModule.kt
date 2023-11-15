package com.example.splitandpay.network.di

import com.example.splitandpay.network.SplitAndPayApiService
import com.example.splitandpay.network.BuildConfig

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideSplitAndPayApiService(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

private fun provideSplitAndPayApiService(retrofit: Retrofit): SplitAndPayApiService {
    return retrofit.create(SplitAndPayApiService::class.java)
}
