package com.achsanit.jokesapp

import android.app.Application
import com.achsanit.jokesapp.di.KoinInitializer

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer.init(this)
    }
}