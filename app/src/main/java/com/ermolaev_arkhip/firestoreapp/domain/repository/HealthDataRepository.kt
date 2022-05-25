package com.ermolaev_arkhip.firestoreapp.domain.repository

import com.ermolaev_arkhip.firestoreapp.domain.model.HealthData
import com.ermolaev_arkhip.firestoreapp.domain.utils.GetState
import com.ermolaev_arkhip.firestoreapp.domain.utils.LoadState
import kotlinx.coroutines.flow.Flow

interface HealthDataRepository {

    fun saveHealthData(healthData: HealthData): Flow<LoadState>
    fun getListOfHealthData(): Flow<GetState>
}