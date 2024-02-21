package com.trekkstay.hotel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hotel.di.startKoinDependencyInjection
import com.trekkstay.hotel.ui.router.AppRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoinDependencyInjection(this)
        setContent {
            AppRouter()
        }
    }
}
