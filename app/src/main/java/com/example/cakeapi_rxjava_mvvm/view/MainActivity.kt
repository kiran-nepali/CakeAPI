package com.example.cakeapi_rxjava_mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cakeapi_rxjava_mvvm.R
import com.example.cakeapi_rxjava_mvvm.model.Cake

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.getCakeInfo()
        val cakeinfo:MutableLiveData<List<Cake>>? = viewModel.passToMainActivity()
        cakeinfo?.observe(this,object:Observer<List<Cake>>{
            override fun onChanged(t: List<Cake>?) {
                Log.d("res",t!![0].title
                )
            }
        })
    }
}
