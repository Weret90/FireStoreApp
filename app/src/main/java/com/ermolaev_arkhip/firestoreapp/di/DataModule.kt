package com.ermolaev_arkhip.firestoreapp.di

import com.ermolaev_arkhip.firestoreapp.data.HealthDataRepositoryImpl
import com.ermolaev_arkhip.firestoreapp.domain.repository.HealthDataRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {

    @Singleton
    @Binds
    fun bindHealthRepository(impl: HealthDataRepositoryImpl): HealthDataRepository
}