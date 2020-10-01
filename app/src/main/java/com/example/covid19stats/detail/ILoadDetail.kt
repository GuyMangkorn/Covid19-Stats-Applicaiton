package com.example.covid19stats.detail

import com.example.covid19stats.recyclercovid.StatsParcel

interface ILoadDetail {
    interface ViewDetail{
        fun result(arr:List<DetailObject>)
    }
    interface PresenterDetail{
        fun loadData(arr:StatsParcel)
    }
}