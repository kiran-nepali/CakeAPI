package com.example.cakeapi_rxjava_mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cakeapi_rxjava_mvvm.R
import com.example.cakeapi_rxjava_mvvm.database.CakeAppDatabase
import com.example.cakeapi_rxjava_mvvm.dependency_injection.application.MyApplication
import com.example.cakeapi_rxjava_mvvm.dependency_injection.component.AppComponent
import com.example.cakeapi_rxjava_mvvm.dependency_injection.component.DaggerAppComponent
import com.example.cakeapi_rxjava_mvvm.dependency_injection.network_module.NetworkModule
import com.example.cakeapi_rxjava_mvvm.model.Cake
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainActivityViewModelfactory: MainActivityViewModelFactory

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var roomdb: CakeAppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder()
            .networkModule(NetworkModule(application as MyApplication))
            .build()
            .inject(this)

        viewModel = ViewModelProviders.of(this,mainActivityViewModelfactory).get(MainActivityViewModel::class.java)
        viewModel.getCakeInfo()

        val cakeinfo:MutableLiveData<List<Cake>>? = viewModel.passToMainActivity()
        val showProgressbar:MutableLiveData<Boolean> = viewModel.showProgress()
        roomdb = CakeAppDatabase.getInstance(applicationContext)

        cakeinfo?.observe(this,object:Observer<List<Cake>>{
            override fun onChanged(t: List<Cake>?) {
                Log.d("res",t!![0].title)
                Log.d("desc",t!![0].desc)
                recyclerview.layoutManager= LinearLayoutManager(this@MainActivity)
                recyclerview.adapter = Adapter(t)
            }
        })

        viewModel.showSuccess.observe(this, Observer {

            if (it == true){
                Toast.makeText(this,"Added user successfully", Toast.LENGTH_SHORT).show()
                Log.d("success","Data inserted ")
            }else{
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()

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

        viewModel.getCakeRoomInfo()
        val roomcake: MutableLiveData<List<Cake>>? = viewModel.passToRoom()
        roomcake?.observe(this,object:Observer<List<Cake>>{
            override fun onChanged(t: List<Cake>?) {
                Log.d("roomtitle", t!![0].title)
            }

        })

    }
}
