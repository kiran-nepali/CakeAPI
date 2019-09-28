package com.example.cakeapi_rxjava_mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cakeapi_rxjava_mvvm.R
import com.example.cakeapi_rxjava_mvvm.common.loadUrl
import com.example.cakeapi_rxjava_mvvm.model.Cake
import kotlinx.android.synthetic.main.cardview.view.*

class Adapter(private val cake:List<Cake>):RecyclerView.Adapter<CakeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        return CakeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false))
    }

    override fun getItemCount(): Int {
        return cake.size
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        holder.title.text = cake[position].title
        holder.desc.text = cake[position].desc
        holder.img.loadUrl(cake[position].image)
    }

}

class CakeViewHolder(view:View):RecyclerView.ViewHolder(view){
    var title = view.tv_title
    var desc = view.tv_desc
    var img = view.iv_cakePicture
}