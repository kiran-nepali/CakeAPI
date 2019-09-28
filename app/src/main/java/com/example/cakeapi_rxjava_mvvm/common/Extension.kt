package com.example.cakeapi_rxjava_mvvm.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url:String){
    return Picasso.get()
        .load(url)
        .into(this)
}