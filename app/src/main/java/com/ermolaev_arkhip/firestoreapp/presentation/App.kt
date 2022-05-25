package com.ermolaev_arkhip.firestoreapp.presentation

import android.app.Application
import com.ermolaev_arkhip.firestoreapp.di.AppComponent
import com.ermolaev_arkhip.firestoreapp.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(context = this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}