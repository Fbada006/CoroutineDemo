package com.example.coroutinedemo.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    fun build(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/coins/")
        .client(HttpClient.create(LoggingInterceptor.create()))
        .addConverterFactory(MoshiConverterFactory.create(MoshiCreator.create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}