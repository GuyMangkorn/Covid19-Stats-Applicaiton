package com.example.covid19stats.api

import com.example.covid19stats.recyclercovid.ISO2object
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val ISO_URL = "https://api.covid19api.com/"

interface ISO2API {

    @GET("countries")
    fun getISO2() : Call<ArrayList<ISO2object>>

    companion object{
        operator fun invoke() : ISO2API {
            return Retrofit.Builder()
                .baseUrl(ISO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ISO2API::class.java)
        }
    }
}