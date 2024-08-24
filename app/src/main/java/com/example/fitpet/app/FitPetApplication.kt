package com.example.fitpet.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitPetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}