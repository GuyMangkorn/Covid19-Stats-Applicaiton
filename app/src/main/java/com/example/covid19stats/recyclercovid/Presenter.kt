package com.example.covid19stats.recyclercovid

import android.util.Log
import com.example.covid19stats.api.ISO2API
import com.example.covid19stats.api.StatsAPI
import com.example.covid19stats.api.WorldAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Comparator
import kotlin.collections.ArrayList


class Presenter(private val context: ILoadData.ViewStats?): ILoadData.PresenterStats {
    private var a: ArrayList<StatsObject>? = ArrayList()
    private var b: ArrayList<ISO2object>? = ArrayList()
    private var iso2:String = ""
    private var bit:Int = 0
    override fun loadData() {

        StatsAPI().getData().enqueue(object : Callback<ArrayList<StatsObject>> {
            override fun onResponse(
                call: Call<ArrayList<StatsObject>>,
                response: Response<ArrayList<StatsObject>>
            ) {
                a = response.body()
                bit = 0
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

    override fun loadDataHome(iso: String) {
        iso2 = iso
        StatsAPI().getData().enqueue(object : Callback<ArrayList<StatsObject>> {
            override fun onResponse(
                call: Call<ArrayList<StatsObject>>,
                response: Response<ArrayList<StatsObject>>
            ) {
                a = response.body()!!
                bit = 1
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

    override fun loadDataWorld() {
        WorldAPI().getWorldData().enqueue(object : Callback<ArrayList<StatsObject>> {
            override fun onResponse(
                call: Call<ArrayList<StatsObject>>,
                response: Response<ArrayList<StatsObject>>
            ) {
                a = response.body()!!
                context!!.resultData(a!!)
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
                when(bit) {
                    0 -> setISO2(b!!, a!!)
                    1 -> setISO2Home(b!!,a!!)
                }
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
    fun setISO2Home(arr: ArrayList<ISO2object>, arr2: ArrayList<StatsObject>) {
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
        for (j in 0 until indexArr.size - 1) {
            if(j>0){a!!.removeAt(indexArr[j]-j)}else {
                a!!.removeAt(indexArr[j])
            }
        }
        val index = arr2.map { T -> T.ISO2 == iso2 }.indexOf(element = true)
        val list:ArrayList<StatsObject> = ArrayList()
        list.add(a!![index])
        context!!.resultData(list)
    }
}