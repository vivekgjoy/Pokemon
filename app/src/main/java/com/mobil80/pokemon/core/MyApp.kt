package com.mobil80.pokemon.core

import android.app.Application
import com.mobil80.pokemon.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
        Timber.plant(Timber.DebugTree())
    }
}