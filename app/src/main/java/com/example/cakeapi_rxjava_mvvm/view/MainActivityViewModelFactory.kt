package com.example.cakeapi_rxjava_mvvm.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cakeapi_rxjava_mvvm.network.GetCakeRequest

class MainActivityViewModelFactory(private val clientInterface:GetCakeRequest, private val application:Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(application,clientInterface) as T
    }

}