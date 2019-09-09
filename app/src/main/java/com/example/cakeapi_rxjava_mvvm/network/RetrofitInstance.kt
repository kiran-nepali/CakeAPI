package com.example.cakeapi_rxjava_mvvm.network

import com.example.cakeapi_rxjava_mvvm.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    val retrofitInstance: Retrofit
    get(){
        val loggingInterceptor = HttpLoggingInterceptor()
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}