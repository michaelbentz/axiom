package com.michaelbentz.axiom

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Axiom : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}