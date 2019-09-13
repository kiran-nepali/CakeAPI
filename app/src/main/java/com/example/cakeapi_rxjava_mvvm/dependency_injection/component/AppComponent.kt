package com.example.cakeapi_rxjava_mvvm.dependency_injection.component

import com.example.cakeapi_rxjava_mvvm.dependency_injection.application.MyApplication
import com.example.cakeapi_rxjava_mvvm.dependency_injection.network_module.NetworkModule
import com.example.cakeapi_rxjava_mvvm.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface AppComponent {
    fun inject(myApplication: MyApplication)
    fun inject(mainActivity: MainActivity)
}