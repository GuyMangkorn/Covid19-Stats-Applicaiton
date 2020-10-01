package com.example.covid19stats.detail


class DetailObject (val country:String,
                    var cases:String,
                    val todayCases:String,
                    val death:String,
                    val todayDeath:String,
                    val recovered:String,
                    val active:String,
                    val critical:String,
                    val casesPerOneMillion:String,
                    val deathsPerOneMillion:String,
                    val totalTests:String,
                    val testPerOneMillion:String,
                    var ISO2:String) {
}