package com.example.covid19stats.api

import com.example.covid19stats.recyclercovid.StatsObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val WORLD_URL = "https://coronavirus-19-api.herokuapp.com/"

interface WorldAPI {
    @GET("countries")
    fun getWorldData(): Call<ArrayList<StatsObject>>
    companion object {
        operator fun invoke(): WorldAPI {
            return Retrofit.Builder().baseUrl(WORLD_URL).addConverterFactory(GsonConverterFactory.create()).build().create(WorldAPI::class.java)
        }
    }
}