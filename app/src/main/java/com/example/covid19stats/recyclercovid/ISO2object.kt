package com.example.covid19stats.recyclercovid

import com.google.gson.annotations.SerializedName

class ISO2object(          @SerializedName("Country")
                           val country:String,
                           @SerializedName("Slug")
                            val slug:String,
                           @SerializedName("ISO2")
                           var ISO2:String){}