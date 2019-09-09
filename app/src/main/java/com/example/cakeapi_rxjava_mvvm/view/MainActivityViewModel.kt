package com.example.cakeapi_rxjava_mvvm.view

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.cakeapi_rxjava_mvvm.model.Cake
import com.example.cakeapi_rxjava_mvvm.network.GetCakeRequest
import com.example.cakeapi_rxjava_mvvm.network.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.create
import java.util.*

class MainActivityViewModel:ViewModel() {

    val cakeObserver = CakeObserver()
    var cakelist: List<Cake>? = null
    val compositeDisposable = CompositeDisposable()

    fun getCakeInfo(){
        val cakeRequest = RetrofitInstance().retrofitInstance.create(GetCakeRequest::class.java)
        val call: Observable<List<Cake>> = cakeRequest.CakeRequest()
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(cakeObserver)
    }

    private fun CakeObserver() : io.reactivex.Observer<List<Cake>> {
        return object:io.reactivex.Observer<List<Cake>>{
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable
            }

            override fun onNext(t: List<Cake>) {
                //passToMainActivity(t)
                cakelist = t
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

     fun passToMainActivity():List<Cake>?{
         Log.d("res",cakelist!![1].title)
         return cakelist
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("destroy","ViewModel Destroyed")
    }
}