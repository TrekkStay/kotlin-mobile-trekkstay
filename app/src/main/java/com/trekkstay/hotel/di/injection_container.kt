package com.example.hotel.di
import com.trekkstay.hotel.MainActivity
import com.trekkstay.hotel.feature.authenticate.domain.usecases.LoginUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { LoginUseCase(get()) }


}

fun startKoinDependencyInjection(applicationContext: MainActivity) {
    startKoin {
        androidContext(applicationContext)
        modules(appModule)
    }
}