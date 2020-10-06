package com.example.covid19stats

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.covid19stats.recyclercovid.ILoadData
import com.example.covid19stats.recyclercovid.Presenter
import com.example.covid19stats.recyclercovid.StatsObject
import com.google.android.material.card.MaterialCardView
import java.text.DecimalFormat
import java.text.NumberFormat

class WorldFragment : Fragment() ,ILoadData.ViewStats{
    private lateinit var cardView:MaterialCardView
    private lateinit var presenter:Presenter
    private lateinit var worldImage:ImageView
    private lateinit var totalCases:TextView
    private lateinit var todayCases:TextView
    private lateinit var totalDeaths:TextView
    private lateinit var todayDeaths:TextView
    private lateinit var totalRecovery:TextView
    private lateinit var totalActive:TextView
    private lateinit var totalCritical:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=  inflater.inflate(R.layout.world_fragment,container,false)
        worldImage = view.findViewById(R.id.imageWorld)
        presenter = Presenter(this)
        presenter.loadDataWorld()
        totalCases = view.findViewById(R.id.worldCases)
        todayCases = view.findViewById(R.id.worldTodayCases)
        totalDeaths = view.findViewById(R.id.worldDeath)
        todayDeaths = view.findViewById(R.id.worldTodayDeath)
        totalRecovery = view.findViewById(R.id.worldRecovers)
        totalActive = view.findViewById(R.id.worldActive)
        totalCritical = view.findViewById(R.id.worldCritical)
        worldImage.animation = AnimationUtils.loadAnimation(context,R.anim.fade_transitions)
        worldImage.setImageResource(R.drawable.world2)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun resultData(arr: ArrayList<StatsObject>) {
        val format: NumberFormat = DecimalFormat("#,###,###,###")
        totalCases.text = "${getString(R.string.totalCases)} : ${format.format(arr[0].cases)}"
        todayCases.text = "${getString(R.string.todayCases)} : ${format.format(arr[0].todayCases)}"
        totalDeaths.text = "${getString(R.string.totalDeaths)} : ${format.format(arr[0].death)}"
        todayDeaths.text = "${getString(R.string.todayDeaths)} : ${format.format(arr[0].todayDeath)}"
        totalRecovery.text = "${getString(R.string.totalRecovers)} : ${format.format(arr[0].recovered)}"
        totalActive.text = "${getString(R.string.totalActive)} : ${format.format(arr[0].active)}"
        totalCritical.text = "${getString(R.string.totalCritical)} : ${format.format(arr[0].critical)}"
    }
}