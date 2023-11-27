package com.achsanit.jokesapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {

    fun init(app: Application) {
        startKoin {
            androidContext(app)
            modules(
                listOf(repoModule, networkModule)
            )
        }
    }

}