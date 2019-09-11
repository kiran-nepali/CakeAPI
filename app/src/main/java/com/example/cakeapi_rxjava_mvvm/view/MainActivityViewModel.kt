package com.example.cakeapi_rxjava_mvvm.view

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cakeapi_rxjava_mvvm.database.CakeAppDatabase
import com.example.cakeapi_rxjava_mvvm.model.Cake
import com.example.cakeapi_rxjava_mvvm.network.GetCakeRequest
import com.example.cakeapi_rxjava_mvvm.network.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    val cakeObserver = CakeObserver()
    private var cakelist: MutableLiveData<List<Cake>>? = MutableLiveData()
    val compositeDisposable = CompositeDisposable()
    private var progressbar: MutableLiveData<Boolean> = MutableLiveData()
    val cakeRoomObserver = CakeRoomObserver()
    private var insertlist: MutableLiveData<List<Cake>>? = MutableLiveData()
    val cakeRequest = CakeAppDatabase.getInstance(application).cakeDAO()
    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun insertCakeInfo(cake: List<Cake>){
        cakeRequest.insertAll(cake)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({showSuccess.value = true},{
                Log.i("ViewModel error",it.message)
                showSuccess.value=false})
    }

    fun getCakeRoomInfo(){
        val getRequest:Observable<List<Cake>> = cakeRequest.getAll()
        getRequest
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(cakeRoomObserver)
    }
    private fun CakeRoomObserver():io.reactivex.Observer<List<Cake>>{
        return object :io.reactivex.Observer<List<Cake>>{
            override fun onComplete() {
                Log.d("emitted","all items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Cake>) {
                insertlist?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("errorcakeroom",e.message)
            }
        }
    }

    fun passToRoom():MutableLiveData<List<Cake>>?{
        return insertlist
    }
    fun getCakeInfo(){
        progressbar.value = true
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
                Log.d("emitted","all items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Cake>) {
                cakelist?.value = t
                insertCakeInfo(t)
                progressbar.value = false

            }

            override fun onError(e: Throwable) {
                Log.d("errormsg",e.message)
            }

        }
    }

     fun passToMainActivity():MutableLiveData<List<Cake>>?{
         return cakelist
    }

    fun showProgress():MutableLiveData<Boolean>{
        return progressbar
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("destroy","ViewModel Destroyed")
    }
}