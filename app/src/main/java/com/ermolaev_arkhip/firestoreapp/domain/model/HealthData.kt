package com.ermolaev_arkhip.firestoreapp.domain.model

data class HealthData(
    val pressure: Int = -1,
    val pulse: Int = -1,
    val time: String = "",
)
