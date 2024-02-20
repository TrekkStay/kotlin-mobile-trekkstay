package com.example.hotel.di
import android.app.Application
import com.example.hotel.feature.authenticate.domain.usecases.LoginUseCase
import com.example.hotel.feature.authenticate.presentation.states.AuthState
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { LoginUseCase(get()) }


}

fun startKoinDependencyInjection(applicationContext: Application) {
    startKoin {
        androidContext(applicationContext)
        modules(appModule)
    }
}