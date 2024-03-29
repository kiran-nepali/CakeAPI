package com.example.cakeapi_rxjava_mvvm.network

import com.example.cakeapi_rxjava_mvvm.model.Cake
import io.reactivex.Observable
import retrofit2.http.GET

interface GetCakeRequest {

    @GET("t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/waracle_cake-android-client")
    fun CakeRequest(): Observable<List<Cake>>
}