package com.example.cakeapi_rxjava_mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cakeapi_rxjava_mvvm.model.Cake


@Database(entities = arrayOf(Cake::class), version = 1)

    abstract class CakeAppDatabase:RoomDatabase() {
        abstract fun cakeDAO():CakeDataAccess
        companion object{
            private var INSTANCE:CakeAppDatabase? = null
            fun getInstance(context: Context): CakeAppDatabase{
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CakeAppDatabase::class.java,
                        "cakedb"
                    )
                        .build()
                }
                return INSTANCE as CakeAppDatabase
            }
        }
}