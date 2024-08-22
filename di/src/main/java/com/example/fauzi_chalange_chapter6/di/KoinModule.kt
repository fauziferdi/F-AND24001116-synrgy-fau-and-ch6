package com.example.fauzi_chalange_chapter6.di

import android.content.Context
import android.location.LocationManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.example.fauzi_chalange_chapter6.data.datasource.AuthLocalDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.AuthRemoteDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.PlayerLocalDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.PlayerRemoteDataSource
import com.example.fauzi_chalange_chapter6.data.datasource.local.AuthLocalDataSourceImpl
import com.example.fauzi_chalange_chapter6.data.datasource.local.PlayerLocalDataSourceImpl
import com.example.fauzi_chalange_chapter6.data.datasource.local.dataStore
import com.example.fauzi_chalange_chapter6.data.datasource.local.room.PlayerDao
import com.example.fauzi_chalange_chapter6.data.datasource.local.room.RoomDatabase
import com.example.fauzi_chalange_chapter6.data.datasource.remote.AuthRemoteDataSourceImpl
import com.example.fauzi_chalange_chapter6.data.datasource.remote.PlayerRemoteDataSourceImpl
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.ApiPlayerService
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.ReqresService
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.providePlayerService
import com.example.fauzi_chalange_chapter6.data.datasource.remote.retrofit.provideReqresService
import com.example.fauzi_chalange_chapter6.data.repository.AuthRepositoryImpl
import com.example.fauzi_chalange_chapter6.data.repository.PlayerRepositoryImpl
import com.example.fauzi_chalange_chapter6.domain.repository.AuthRepository
import com.example.fauzi_chalange_chapter6.domain.repository.PlayerRepository
import com.example.fauzi_chalange_chapter6.domain.usecase.LoginUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val koinModule = module {
    single<LocationManager> { androidContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    single<LoginUseCase> { LoginUseCase(repository = get()) }
    single<AuthRepository> { AuthRepositoryImpl(authLocalDataSource = get(), authRemoteDataSource = get()) }
    single<PlayerRepository> { PlayerRepositoryImpl(remoteDataSource = get(), localDataSource = get()) }
    single<PlayerLocalDataSource> { PlayerLocalDataSourceImpl(playerDao = get()) }
    single<RoomDatabase> { Room.databaseBuilder(context = get(), name = RoomDatabase.NAME, klass = RoomDatabase::class.java).build() }
    single<PlayerDao> { (get() as RoomDatabase).playerDao() }
    single<PlayerRemoteDataSource> { PlayerRemoteDataSourceImpl(apiPlayerService = get()) }
    single<ApiPlayerService> { providePlayerService(get()) }
    single<AuthLocalDataSource> { AuthLocalDataSourceImpl(dataStore = get()) }
    single<DataStore<Preferences>> { androidContext().dataStore }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(reqresService = get()) }
    single<ReqresService> { provideReqresService(get()) }
}