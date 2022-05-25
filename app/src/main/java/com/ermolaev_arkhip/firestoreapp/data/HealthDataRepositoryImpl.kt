package com.ermolaev_arkhip.firestoreapp.data

import com.ermolaev_arkhip.firestoreapp.data.network.Firestore
import com.ermolaev_arkhip.firestoreapp.domain.model.HealthData
import com.ermolaev_arkhip.firestoreapp.domain.repository.HealthDataRepository
import javax.inject.Inject

class HealthDataRepositoryImpl @Inject constructor(
    private val firestore: Firestore
) : HealthDataRepository {

    override fun saveHealthData(healthData: HealthData) =
        firestore.addHealthData(healthData)

    override fun getListOfHealthData() =
        firestore.getHealthDataList()
}