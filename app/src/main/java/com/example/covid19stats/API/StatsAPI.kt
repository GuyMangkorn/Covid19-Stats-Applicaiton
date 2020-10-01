package com.example.covid19stats.API

import com.example.covid19stats.recyclercovid.StatsObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://coronavirus-19-api.herokuapp.com/"
interface StatsAPI {
        @GET("countries")
        fun getData(): Call<ArrayList<StatsObject>>
        companion object {
            operator fun invoke(): StatsAPI {
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(StatsAPI::class.java)
            }
        }
}
