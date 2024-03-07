package com.trekkstay.hotel

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.trekkstay.hotel.di.startKoinDependencyInjection
import com.trekkstay.hotel.config.router.AppRouter
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val authStateManager: AuthViewModel by inject()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoinDependencyInjection(this)
        setContent {
            AppRouter(authStateManager)
        }
    }
}
