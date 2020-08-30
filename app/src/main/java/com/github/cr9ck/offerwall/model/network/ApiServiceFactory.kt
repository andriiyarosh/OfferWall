package com.github.cr9ck.offerwall.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceFactory {

    private val BASE_URL: String = "https://demo0040494.mockable.io/"
    private val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <SClass> createService(serviceClass: Class<SClass>) = retrofitClient.create(serviceClass)
}