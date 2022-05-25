package com.ermolaev_arkhip.firestoreapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ermolaev_arkhip.firestoreapp.domain.repository.HealthDataRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repository: HealthDataRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}