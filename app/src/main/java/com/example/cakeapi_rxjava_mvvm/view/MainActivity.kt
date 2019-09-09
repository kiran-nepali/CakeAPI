package com.example.cakeapi_rxjava_mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cakeapi_rxjava_mvvm.R
import com.example.cakeapi_rxjava_mvvm.model.Cake
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        viewModel.getCakeInfo()
        val cakeinfo:MutableLiveData<List<Cake>>? = viewModel.passToMainActivity()
        val showProgressbar:MutableLiveData<Boolean> = viewModel.showProgress()

        cakeinfo?.observe(this,object:Observer<List<Cake>>{
            override fun onChanged(t: List<Cake>?) {
                Log.d("res",t!![0].title)
                Log.d("desc",t!![0].desc)
                recyclerview.layoutManager= LinearLayoutManager(this@MainActivity)
                recyclerview.adapter = Adapter(t)
            }
        })

        showProgressbar.observe(this,object :Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if (t == true){
                    pgbar.visibility = View.VISIBLE
                }
                if (t == false){
                    pgbar.visibility = View.GONE
                }
            }

        })

    }
}
