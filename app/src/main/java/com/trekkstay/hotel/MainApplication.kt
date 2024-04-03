package com.trekkstay.hotel

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.trekkstay.hotel.di.startKoinDependencyInjection
import com.trekkstay.hotel.config.router.AppRouter
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {


    private val authStateManager: AuthViewModel by inject()
    private val empAuthStateManager: EmpAuthViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoinDependencyInjection(this)
        setContent {
            AppRouter(authStateManager,empAuthStateManager,this@MainActivity)
        }
    }


}
