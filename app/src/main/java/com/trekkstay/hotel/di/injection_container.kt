package com.trekkstay.hotel.di
import com.trekkstay.hotel.MainActivity
import com.trekkstay.hotel.core.network.client.Client
import com.trekkstay.hotel.feature.authenticate.data.datasources.AuthRemoteDataSource
import com.trekkstay.hotel.feature.authenticate.data.datasources.AuthRemoteDataSourceImpl
import com.trekkstay.hotel.feature.authenticate.data.repositories.AuthRepoImpl
import com.trekkstay.hotel.feature.authenticate.domain.repositories.AuthRepo
import com.trekkstay.hotel.feature.authenticate.domain.usecases.EmpCreateUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.EmpLoginUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.EmpRegisterUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.LoginUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.RegisterUseCase
import com.trekkstay.hotel.feature.authenticate.domain.usecases.ViewEmpUseCase
import com.trekkstay.hotel.feature.authenticate.presentation.states.AuthViewModel
import com.trekkstay.hotel.feature.authenticate.presentation.states.EmpAuthViewModel
import com.trekkstay.hotel.feature.hotel.data.datasources.AttractionRemoteDataSource
import com.trekkstay.hotel.feature.hotel.data.datasources.AttractionRemoteDataSourceImpl
import com.trekkstay.hotel.feature.hotel.data.datasources.HotelRemoteDataSource
import com.trekkstay.hotel.feature.hotel.data.datasources.HotelRemoteDataSourceImpl
import com.trekkstay.hotel.feature.hotel.data.datasources.LocationRemoteDataSource
import com.trekkstay.hotel.feature.hotel.data.datasources.LocationRemoteDataSourceImpl
import com.trekkstay.hotel.feature.hotel.data.datasources.MediaRemoteDataSource
import com.trekkstay.hotel.feature.hotel.data.datasources.MediaRemoteDataSourceImpl
import com.trekkstay.hotel.feature.hotel.data.datasources.RoomRemoteDataSource
import com.trekkstay.hotel.feature.hotel.data.datasources.RoomRemoteDataSourceImpl
import com.trekkstay.hotel.feature.hotel.data.datasources.SearchRemoteDataSource
import com.trekkstay.hotel.feature.hotel.data.datasources.SearchRemoteDataSourceImpl
import com.trekkstay.hotel.feature.hotel.data.repositories.AttractionRepoImpl
import com.trekkstay.hotel.feature.hotel.data.repositories.HotelRepoImpl
import com.trekkstay.hotel.feature.hotel.data.repositories.LocationRepoImpl
import com.trekkstay.hotel.feature.hotel.data.repositories.MediaRepoImpl
import com.trekkstay.hotel.feature.hotel.data.repositories.RoomRepoImpl
import com.trekkstay.hotel.feature.hotel.data.repositories.SearchRepoImpl
import com.trekkstay.hotel.feature.hotel.domain.repositories.AttractionRepo
import com.trekkstay.hotel.feature.hotel.domain.repositories.HotelRepo
import com.trekkstay.hotel.feature.hotel.domain.repositories.LocationRepo
import com.trekkstay.hotel.feature.hotel.domain.repositories.MediaRepo
import com.trekkstay.hotel.feature.hotel.domain.repositories.RoomRepo
import com.trekkstay.hotel.feature.hotel.domain.repositories.SearchRepo
import com.trekkstay.hotel.feature.hotel.domain.usecases.hotel.CreateHotelUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.hotel.GetHotelIdUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.hotel.HotelDetailUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.hotel.ViewHotelUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.location.ViewDistrictUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.location.ViewProvinceUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.location.ViewWardUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.media.UploadMediaUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.room.CreateRoomUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.room.ViewRoomUseCase
import com.trekkstay.hotel.feature.hotel.domain.usecases.search.ViewDestinationUseCase
import com.trekkstay.hotel.feature.hotel.presentation.states.attraction.AttractionViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.hotel.HotelViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.location.LocationViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.media.MediaViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.room.RoomViewModel
import com.trekkstay.hotel.feature.hotel.presentation.states.search.SearchViewModel
import com.trekkstay.hotel.feature.reservation.data.datasources.ReservationRemoteDataSource
import com.trekkstay.hotel.feature.reservation.data.datasources.ReservationRemoteDataSourceImpl
import com.trekkstay.hotel.feature.reservation.data.repositories.ReservationRepoImpl
import com.trekkstay.hotel.feature.reservation.domain.repositories.ReservationRepo
import com.trekkstay.hotel.feature.reservation.domain.usecases.CreateReservationUseCase
import com.trekkstay.hotel.feature.reservation.presentation.states.ReservationViewModel
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
    single { EmpCreateUseCase(get()) }
    single { ViewEmpUseCase(get()) }
    single <AuthRemoteDataSource>{ AuthRemoteDataSourceImpl(get(),get()) }
    single <AuthRepo>{ AuthRepoImpl(get()) }

    single { HotelViewModel(get()) }
    single { CreateHotelUseCase(get()) }
    single { GetHotelIdUseCase(get())}
    single { ViewHotelUseCase(get())}
    single { HotelDetailUseCase(get()) }
    single <HotelRemoteDataSource>{ HotelRemoteDataSourceImpl(get(),get()) }
    single <HotelRepo>{ HotelRepoImpl(get())}

    single { RoomViewModel(get()) }
    single { CreateRoomUseCase(get()) }
    single { ViewRoomUseCase(get()) }
    single <RoomRemoteDataSource>{ RoomRemoteDataSourceImpl(get(),get()) }
    single <RoomRepo>{ RoomRepoImpl(get()) }

    single { MediaViewModel(get()) }
    single { UploadMediaUseCase(get()) }
    single <MediaRemoteDataSource>{ MediaRemoteDataSourceImpl(get()) }
    single <MediaRepo>{ MediaRepoImpl(get()) }

    single { SearchViewModel(get(),get()) }
    single { ViewDestinationUseCase(get()) }
    single <SearchRemoteDataSource>{ SearchRemoteDataSourceImpl(get(),get()) }
    single <SearchRepo>{ SearchRepoImpl(get()) }

    single { LocationViewModel(get()) }
    single { ViewProvinceUseCase(get()) }
    single { ViewDistrictUseCase(get()) }
    single { ViewWardUseCase(get()) }
    single <LocationRemoteDataSource>{ LocationRemoteDataSourceImpl(get()) }
    single <LocationRepo>{ LocationRepoImpl(get())}

    single { ReservationViewModel(get()) }
    single { CreateReservationUseCase(get()) }
    single <ReservationRemoteDataSource>{ ReservationRemoteDataSourceImpl(get(),get()) }
    single <ReservationRepo>{ ReservationRepoImpl(get()) }

    single { AttractionViewModel(get()) }
//    single { CreateReservationUseCase(get()) }
    single <AttractionRemoteDataSource>{ AttractionRemoteDataSourceImpl(get(),get()) }
    single <AttractionRepo>{ AttractionRepoImpl(get()) }

    single { Client(get()) }
    single { OkHttpClient() }
}

fun startKoinDependencyInjection(applicationContext: MainActivity) {
    startKoin {
        androidContext(applicationContext)
        modules(appModule)
    }
}