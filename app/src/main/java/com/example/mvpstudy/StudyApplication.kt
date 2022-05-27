package com.example.mvpstudy

import android.app.Application
import com.example.mvpstudy.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StudyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StudyApplication)
            modules(MainModule)
        }
    }
}