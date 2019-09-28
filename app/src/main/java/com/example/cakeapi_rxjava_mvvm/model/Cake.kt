package com.example.cakeapi_rxjava_mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cake")
data class Cake (
    @PrimaryKey @SerializedName("title") val title : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("image") val image : String
)