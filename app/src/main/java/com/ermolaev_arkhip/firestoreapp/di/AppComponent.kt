package com.ermolaev_arkhip.firestoreapp.di

import android.content.Context
import com.ermolaev_arkhip.firestoreapp.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}