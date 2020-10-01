package com.example.covid19stats.detail

import android.util.Log
import com.example.covid19stats.recyclercovid.StatsParcel
import java.text.DecimalFormat
import java.text.NumberFormat

class PresenterDetail(private val context:ILoadDetail.ViewDetail) : ILoadDetail.PresenterDetail {
    override fun loadData(arr: StatsParcel) {
        val newArr:ArrayList<DetailObject> = ArrayList()
        val format:NumberFormat = DecimalFormat("#,###,###,###")
        val obj = DetailObject(arr.country,format.format(arr.cases),format.format(arr.todayCases),
        format.format(arr.death),format.format(arr.todayDeath),format.format(arr.recovered),format.format(arr.active),
        format.format(arr.critical),arr.casesPerOneMillion.toString(),arr.deathsPerOneMillion.toString(),arr.totalTests.toString(),arr.testPerOneMillion.toString(),arr.ISO2)
        newArr.add(obj)
        context.result(newArr)
    }

}