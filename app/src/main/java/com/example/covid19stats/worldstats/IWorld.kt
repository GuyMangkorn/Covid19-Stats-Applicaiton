package com.example.covid19stats.worldstats

import com.example.covid19stats.recyclercovid.StatsObject

interface IWorld {
    interface ViewWorld{
        fun resultWorld(arr:List<StatsObject>)
    }
    interface PresenterWorld{
        fun loadDataWorld()
    }
}