package com.example.fauzi_chalange_chapter6

import android.app.Application
import com.example.fauzi_chalange_chapter6.di.viewModelModuleFactory
import com.example.fauzi_chalange_chapter6.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(koinModule, viewModelModuleFactory)
        }
    }
}