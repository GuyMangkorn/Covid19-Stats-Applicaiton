package com.example.covid19stats

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19stats.recyclercovid.ILoadData
import com.example.covid19stats.recyclercovid.Presenter
import com.example.covid19stats.recyclercovid.StatsAdapter
import com.example.covid19stats.recyclercovid.StatsObject

class ListFragment :Fragment() , ILoadData.ViewStats {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerLayoutManager: RecyclerView.LayoutManager
    private var dataAPI:ArrayList<StatsObject> = ArrayList()
    private var presenter:Presenter? = null
    //private var contextList:Context = requireContext()
    private fun getDataAPIList(): ArrayList<StatsObject> {
        return dataAPI
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View =  inflater.inflate(R.layout.list_fragment,container,false)
        presenter = Presenter(this)
        super.onCreate(savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerList)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.setHasFixedSize(true)
        recyclerLayoutManager = LinearLayoutManager(requireContext())
         recyclerView.layoutManager = recyclerLayoutManager
        presenter!!.loadData()
        return view
    }

    override fun resultData(arr: ArrayList<StatsObject>) {
        recyclerAdapter = StatsAdapter(arr,requireContext())
        recyclerView.adapter = recyclerAdapter
        recyclerView.setItemViewCacheSize(arr.size)
    }
}