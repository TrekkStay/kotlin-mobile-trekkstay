package com.example.hotel

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hotel.di.startKoinDependencyInjection
import com.example.hotel.ui.theme.HotelTheme

class MainActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinDependencyInjection(this)
    }
}
