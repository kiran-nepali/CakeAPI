package com.example.cakeapi_rxjava_mvvm.database

import androidx.room.*
import com.example.cakeapi_rxjava_mvvm.model.Cake
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface CakeDataAccess {

    @Query("SELECT * FROM cake ")
    fun getAll():Observable<List<Cake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cake:List<Cake>):Completable

    @Delete
    fun delete(cake: Cake)
}