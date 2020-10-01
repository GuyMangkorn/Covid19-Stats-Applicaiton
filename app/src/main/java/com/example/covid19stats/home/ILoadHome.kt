package com.example.covid19stats.home

import com.example.covid19stats.recyclercovid.StatsObject

interface ILoadHome {
    interface ViewHome{
        fun resultData(arr: ArrayList<StatsObject>)
    }
    interface PresenterHome{
        fun loadData(iso:String)
    }
}