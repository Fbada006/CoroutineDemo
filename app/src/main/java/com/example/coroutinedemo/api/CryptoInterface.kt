package com.example.coroutinedemo.api

import com.example.coroutinedemo.models.Crypto
import retrofit2.http.GET

interface CryptoInterface {
    @GET("list")
    suspend fun getAllCoinsData(): List<Crypto>
}