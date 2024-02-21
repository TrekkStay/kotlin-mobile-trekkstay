package com.trekkstay.hotel

import android.app.Application
import com.example.hotel.di.startKoinDependencyInjection

class MainActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinDependencyInjection(this)
    }
}
