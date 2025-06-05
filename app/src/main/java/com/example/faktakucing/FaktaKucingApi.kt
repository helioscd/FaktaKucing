package com.example.faktakucing

import retrofit2.http.GET
import retrofit2.Call

interface FaktaKucingApi {
        @GET("fact")
        fun getCatFact(): Call<CatFactResponse>
}
