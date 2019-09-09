package com.example.cakeapi_rxjava_mvvm.model

import com.google.gson.annotations.SerializedName

data class Cake (

    @SerializedName("title") val title : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("image") val image : String
)