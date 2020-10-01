package com.example.covid19stats

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.covid19stats.detail.DetailObject
import com.example.covid19stats.detail.ILoadDetail
import com.example.covid19stats.detail.PresenterDetail
import com.example.covid19stats.recyclercovid.StatsObject
import com.example.covid19stats.recyclercovid.StatsParcel

class DetailCountry : AppCompatActivity() , ILoadDetail.ViewDetail{
    private lateinit var presenter:PresenterDetail
    private lateinit var textTotalCase:TextView
    private lateinit var textTodayCase:TextView
    private lateinit var textTotalDeaths:TextView
    private lateinit var textTodayDeaths:TextView
    private lateinit var textTotalRecovers:TextView
    private lateinit var textTotalActive:TextView
    private lateinit var textTotalCritical:TextView
    private lateinit var detailCountry:TextView
    private lateinit var flagImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PresenterDetail(this)
        setContentView(R.layout.activity_detail_country)
        flagImage = findViewById(R.id.flagDetail)
        textTotalCase = findViewById(R.id.detailTotalCase)
        textTodayCase = findViewById(R.id.detailTodayCase)
        textTotalDeaths = findViewById(R.id.detailTotalDeath)
        textTodayDeaths = findViewById(R.id.detailTodayDeath)
        textTotalRecovers = findViewById(R.id.detailRecover)
        textTotalActive = findViewById(R.id.detailTotalActive)
        textTotalCritical = findViewById(R.id.detailTotalCritical)
        detailCountry = findViewById(R.id.detailCountry)
        val myList: StatsParcel = intent.getParcelableExtra("data")!!
        presenter.loadData(myList)

    }
    override fun result(arr: List<DetailObject>) {
        Glide.with(this).load("https://www.countryflags.io/${arr[0].ISO2}/shiny/64.png").into(flagImage)
        textTotalCase.text = arr[0].cases
        textTodayCase.text = arr[0].todayCases
        textTotalDeaths.text = arr[0].death
        textTodayDeaths.text = arr[0].todayDeath
        textTotalRecovers.text = arr[0].recovered
        textTotalActive.text = arr[0].active
        textTotalCritical.text = arr[0].critical
        detailCountry.text = arr[0].country
    }
}