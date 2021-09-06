package com.example.roomtrain

import android.app.Application
import androidx.room.Room
import com.example.roomtrain.data.AppDatabase

class App: Application() {

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}