package com.example.covid19stats.recyclercovid

interface ILoadData {
    interface ViewStats{
        fun resultData(arr:ArrayList<StatsObject>)
    }
    interface PresenterStats{
        fun loadData()
        fun loadDataHome(iso:String)
        fun loadDataWorld()

    }
}