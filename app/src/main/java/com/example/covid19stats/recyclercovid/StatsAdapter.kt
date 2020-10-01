package com.example.covid19stats.recyclercovid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import java.util.ArrayList
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.covid19stats.DetailCountry
import com.example.covid19stats.R
import com.google.android.material.card.MaterialCardView
import java.io.Serializable

class StatsAdapter(private val result:ArrayList<StatsObject>,private val context: Context) :
    RecyclerView.Adapter<StatsAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryText:TextView = itemView.findViewById(R.id.textStat)
        private val casesText:TextView = itemView.findViewById(R.id.textCount)
        private val countryFlag:ImageView = itemView.findViewById(R.id.flagImage)
        private val containData:LinearLayout = itemView.findViewById(R.id.containData)
        private val cardList:MaterialCardView = itemView.findViewById(R.id.cardList)
        @SuppressLint("SetTextI18n")
        fun set(position: Int){
            containData.animation = AnimationUtils.loadAnimation(context,R.anim.fade_scale_animations)
            countryText.text = result.elementAt(position).country
            casesText.text = "${context.getString(R.string.todayCases)} : ${result.elementAt(position).todayCases}"
            val iso2 = result.elementAt(position).ISO2
            Glide.with(context).load("https://www.countryflags.io/$iso2/shiny/64.png").into(countryFlag)
            cardList.setOnClickListener() {
                val obj = StatsParcel(result[position].country,result[position].cases,result[position].todayCases,
                            result[position].death,result[position].todayDeath,result[position].recovered,result[position].active,
                            result[position].critical,result[position].casesPerOneMillion,result[position].deathsPerOneMillion,
                            result[position].totalTests,result[position].testPerOneMillion,result[position].ISO2)
                val intent:Intent = Intent(context, DetailCountry::class.java)
                intent.putExtra("data", obj)
                val activityOptions:ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity,countryFlag, ViewCompat.getTransitionName(countryFlag).toString())
                context.startActivity(intent,activityOptions.toBundle())
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemcovidstats, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.set(position)
    }

    override fun getItemCount(): Int {
        return result.size
    }
}
