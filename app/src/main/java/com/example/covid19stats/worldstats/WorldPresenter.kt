package com.example.covid19stats.worldstats

import android.util.Log
import com.example.covid19stats.API.StatsAPI
import com.example.covid19stats.API.WorldAPI
import com.example.covid19stats.recyclercovid.StatsObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WorldPresenter (private val view:IWorld.ViewWorld) : IWorld.PresenterWorld{
    private var a:List<StatsObject> = ArrayList()
    override fun loadDataWorld() {
        WorldAPI().getWorldData().enqueue(object : Callback<List<StatsObject>> {
            override fun onResponse(
                call: Call<List<StatsObject>>,
                response: Response<List<StatsObject>>
            ) {
                a = response.body()!!
                view.resultWorld(a)
            }

            override fun onFailure(call: Call<List<StatsObject>>, t: Throwable) {
                Log.d(
                    "TAG",
                    t.toString()
                )
            }
        })
    }
}