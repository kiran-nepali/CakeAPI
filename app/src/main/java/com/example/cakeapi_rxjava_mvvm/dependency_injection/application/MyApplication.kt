package com.example.cakeapi_rxjava_mvvm.dependency_injection.application

import android.app.Application
import com.example.cakeapi_rxjava_mvvm.dependency_injection.component.AppComponent
import com.example.cakeapi_rxjava_mvvm.dependency_injection.component.DaggerAppComponent
import com.example.cakeapi_rxjava_mvvm.dependency_injection.network_module.NetworkModule

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()



        DaggerAppComponent.builder()
           .networkModule(NetworkModule(this))
            .build()
            .inject(this)
    }
}