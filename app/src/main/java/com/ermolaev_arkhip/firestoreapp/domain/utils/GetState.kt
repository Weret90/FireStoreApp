package com.ermolaev_arkhip.firestoreapp.domain.utils

import com.ermolaev_arkhip.firestoreapp.domain.model.HealthData

sealed class GetState {
    object Loading: GetState()
    data class Error(val throwable: Throwable): GetState()
    data class Success(val list: List<HealthData>): GetState()
}

