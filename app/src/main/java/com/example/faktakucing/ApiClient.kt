package com.example.faktakucing

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
        private const val BASE_URL = "https://catfact.ninja/"

        val instance: FaktaKucingApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(FaktaKucingApi::class.java)
        }
}
