package com.trekkstay.hotel.di
import com.trekkstay.hotel.MainActivity
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.feature.authenticate.data.datasources.AuthRemoteDataSource
import com.trekkstay.hotel.feature.authenticate.data.datasources.AuthRemoteDataSourceImpl
import com.trekkstay.hotel.feature.authenticate.data.repositories.AuthRepoImpl
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import com.trekkstay.hotel.feature.authenticate.domain.usecases.EmpLoginUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.EmpRegisterUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.LoginUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.RegisterUseCase
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { AuthViewModel(get()) }
    single { EmpAuthViewModel(get()) }
    single { LoginUseCase(get()) }
    single { RegisterUseCase(get()) }
    single { EmpLoginUseCase(get()) }
    single { EmpRegisterUseCase(get()) }

    single <AuthRemoteDataSource>{ AuthRemoteDataSourceImpl(get()) }
    single <AuthRepo>{ AuthRepoImpl(get()) }


    single { Client(get()) }
    single { OkHttpClient() }
}

fun startKoinDependencyInjection(applicationContext: MainActivity) {
    startKoin {
        androidContext(applicationContext)
        modules(appModule)
    }
}