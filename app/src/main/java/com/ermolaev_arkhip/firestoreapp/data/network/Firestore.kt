package com.ermolaev_arkhip.firestoreapp.data.network

import com.ermolaev_arkhip.firestoreapp.domain.model.HealthData
import com.ermolaev_arkhip.firestoreapp.domain.utils.GetState
import com.ermolaev_arkhip.firestoreapp.domain.utils.LoadState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class Firestore @Inject constructor() {
    private val db = Firebase.firestore
    private val healthDataTable = db.collection(TABLE_NAME)

    fun addHealthData(healthData: HealthData) =
        flow<LoadState> {
            val data = hashMapOf(
                KEY_TIME to healthData.time,
                KEY_PRESSURE to healthData.pressure,
                KEY_PULSE to healthData.pulse
            )
            healthDataTable.add(data).await()
            emit(LoadState.Success)
        }.catch { error ->
            emit(LoadState.Error(error))
        }

    fun getHealthDataList() =
        flow {
            emit(GetState.Loading)
            emit(GetState.Success(healthDataTable.get().await().documents.mapNotNull {
                it.toObject<HealthData>()
            }))
        }.catch { error ->
            emit(GetState.Error(error))
        }

    companion object {
        private const val TABLE_NAME = "health_data_2"
        private const val KEY_TIME = "time"
        private const val KEY_PRESSURE = "pressure"
        private const val KEY_PULSE = "pulse"
    }
}