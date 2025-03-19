package com.example.neuronest.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class ApiResponse(val success: Boolean, val id: String?)

interface ApiService {

}

fun createApiService(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}