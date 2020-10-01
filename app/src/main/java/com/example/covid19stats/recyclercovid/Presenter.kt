package com.example.covid19stats.recyclercovid

import android.util.Log
import com.example.covid19stats.API.ISO2API
import com.example.covid19stats.API.StatsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Comparator
import kotlin.collections.ArrayList


class Presenter(private val context: ILoadData.ViewStats?): ILoadData.PresenterStats {
    private var a: ArrayList<StatsObject>? = ArrayList()
    private var b: ArrayList<ISO2object>? = ArrayList()
    override fun loadData() {

        StatsAPI().getData().enqueue(object : Callback<ArrayList<StatsObject>> {
            override fun onResponse(
                call: Call<ArrayList<StatsObject>>,
                response: Response<ArrayList<StatsObject>>
            ) {
                a = response.body()
                get()
            }

            override fun onFailure(call: Call<ArrayList<StatsObject>>, t: Throwable) {
                Log.d(
                    "TAG",
                    t.toString()
                )
            }
        })


    }
    private fun get() {
        ISO2API().getISO2().enqueue(object : Callback<ArrayList<ISO2object>> {
            override fun onResponse(
                call: Call<ArrayList<ISO2object>>,
                response: Response<ArrayList<ISO2object>>
            ) {
                b = response.body()
                setISO2(b!!, a!!)
            }

            override fun onFailure(call: Call<ArrayList<ISO2object>>, t: Throwable) {
                Log.d(
                    "TAG",
                    t.toString()
                )
            }
        })

    }

    private var indexArr: ArrayList<Int> = ArrayList()
    fun setISO2(arr: ArrayList<ISO2object>, arr2: ArrayList<StatsObject>) {
        for (i in 0 until arr2.size - 1) {
            val index =
                arr.map { T -> T.country == arr2.elementAt(i).country }.indexOf(element = true)
            if (index != -1) {
                a!!.elementAt(i).ISO2 = arr.elementAt(index).ISO2
            } else {
                when (a!!.elementAt(i).country) {
                    "World" -> { indexArr.add(i) }
                    "USA" -> a!!.elementAt(i).ISO2 = "US"
                    "Russia" -> a!!.elementAt(i).ISO2 = "RU"
                    "Iran" -> a!!.elementAt(i).ISO2 = "IR"
                    "UK" -> a!!.elementAt(i).ISO2 = "GB"
                    "UAE" -> a!!.elementAt(i).ISO2 = "AE"
                    "Venezuela" -> a!!.elementAt(i).ISO2 = "VE"
                    "Czechia" -> a!!.elementAt(i).ISO2 = "CZ"
                    "Palestine" -> a!!.elementAt(i).ISO2 = "PS"
                    "S. Korea" -> a!!.elementAt(i).ISO2 = "KR"
                    "Ivory Coast" -> a!!.elementAt(i).ISO2 = "CI"
                    "North Macedonia" -> a!!.elementAt(i).ISO2 = "MK"
                    "DRC" -> a!!.elementAt(i).ISO2 = "CD"
                    "Cabo Verde" -> a!!.elementAt(i).ISO2 = "CV"
                    "Eswatini" -> a!!.removeAt(i)
                    "Hong Kong" -> a!!.elementAt(i).ISO2 = "HK"
                    "Congo" -> a!!.elementAt(i).ISO2 = "CD"
                    "CAR" -> a!!.elementAt(i).ISO2 = "CF"
                    //"Turks and Caicos" -> a!!.elementAt(i).ISO2 = "TC"
                    "Syria" -> a!!.elementAt(i).ISO2 = "SY"
                    "Vietnam" -> a!!.elementAt(i).ISO2 = "VN"
                    "Tanzania" -> a!!.elementAt(i).ISO2 = "TZ"
                    "Taiwan" -> a!!.elementAt(i).ISO2 = "TW"
                    "Macao" -> a!!.elementAt(i).ISO2 = "MO"
                    "Laos" -> a!!.elementAt(i).ISO2 = "LA"
                    else -> indexArr.add(i)
                }
            }
        }
        Log.d("TAG", a!!.size.toString())
        for (j in 0 until indexArr.size - 1) {
            if(j>0){a!!.removeAt(indexArr[j]-j)}else {
                a!!.removeAt(indexArr[j])
            }
        }
        a!!.sortWith(Comparator { o1, o2 ->
            return@Comparator o2.todayCases - o1.todayCases
        })
        context!!.resultData(a!!)
    }
}