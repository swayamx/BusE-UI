package com.example.buse_ui.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofitnstance {
    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .build()

    val api: com.example.buse_ui.data.Api = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(com.example.buse_ui.data.Api.BASE_URL)
        .client(
            client
        ).build().create(com.example.buse_ui.data.Api::class.java)

}

