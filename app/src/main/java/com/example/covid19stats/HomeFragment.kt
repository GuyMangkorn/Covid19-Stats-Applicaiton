package com.example.covid19stats


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.covid19stats.home.ILoadHome
import com.example.covid19stats.home.PresenterHome
import com.example.covid19stats.recyclercovid.StatsObject
import java.text.DecimalFormat
import java.text.NumberFormat

class HomeFragment : Fragment() , ILoadHome.ViewHome {
    private lateinit var presenter: PresenterHome
    private lateinit var textTotalCase: TextView
    private lateinit var textTodayCase: TextView
    private lateinit var textTotalDeaths: TextView
    private lateinit var textTodayDeaths: TextView
    private lateinit var textTotalRecovers: TextView
    private lateinit var textTotalActive: TextView
    private lateinit var textTotalCritical: TextView
    private lateinit var detailCountry: TextView
    private lateinit var flagImage: ImageView
    private lateinit var flipTotalCases:ViewFlipper
    private lateinit var flipTotalDeath:ViewFlipper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.home_fragment,container,false)
        presenter = PresenterHome(this)
        flipTotalCases = view.findViewById(R.id.flipCaseTitle)
        flipTotalDeath = view.findViewById(R.id.flipDeathTitle)
        flipTotalCases.startFlipping()
        flipTotalDeath.startFlipping()
        flagImage = view.findViewById(R.id.flagDetail)
        textTotalCase = view.findViewById(R.id.homeTotalCases)
        textTodayCase = view.findViewById(R.id.homeTodayCases)
        textTotalDeaths = view.findViewById(R.id.homeTotalDeath)
        textTodayDeaths = view.findViewById(R.id.homeTodayDeath)
        textTotalRecovers = view.findViewById(R.id.homeTotalRecovery)
        textTotalActive = view.findViewById(R.id.homeTotalActive)
        textTotalCritical = view.findViewById(R.id.homeTotalCritical)
        detailCountry = view.findViewById(R.id.detailCountry)
        val iso =(activity as MainActivity).countryISO()
        Log.d("TAG",iso)
        presenter.loadData(iso)
        return view
    }
    override fun resultData(arr: ArrayList<StatsObject>) {
        val format: NumberFormat = DecimalFormat("#,###,###,###")
        Glide.with(this).load("https://www.countryflags.io/${arr[0].ISO2}/shiny/64.png").into(flagImage)
        textTotalCase.text = format.format(arr[0].cases)
        textTodayCase.text = format.format(arr[0].todayCases)
        textTotalDeaths.text = format.format(arr[0].death)
        textTodayDeaths.text = format.format(arr[0].todayDeath)
        textTotalRecovers.text = format.format(arr[0].recovered)
        textTotalActive.text = format.format(arr[0].active)
        textTotalCritical.text = format.format(arr[0].critical)
        detailCountry.text = requireActivity().getString(R.string.yourCountry)
    }



}