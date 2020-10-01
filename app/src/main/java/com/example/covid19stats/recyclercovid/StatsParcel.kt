package com.example.covid19stats.recyclercovid

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatsParcel(val country:String,
                       @SerializedName("cases")
                       var cases:Int,
                       @SerializedName("todayCases")
                       val todayCases:Int,
                       @SerializedName("deaths")
                       val death:Int,
                       @SerializedName("todayDeaths")
                       val todayDeath:Int,
                       @SerializedName("recovered")
                       val recovered:Int,
                       @SerializedName("active")
                       val active:Int,
                       @SerializedName("critical")
                       val critical:Int,
                       @SerializedName("casesPerOneMillion")
                       val casesPerOneMillion:Int,
                       @SerializedName("deathsPerOneMillion")
                       val deathsPerOneMillion:Int,
                       @SerializedName("totalTests")
                       val totalTests:Int,
                       @SerializedName("testsPerOneMillion")
                       val testPerOneMillion:Int,
                       var ISO2:String) : Parcelable {

}