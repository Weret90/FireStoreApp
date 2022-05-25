package com.ermolaev_arkhip.firestoreapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ermolaev_arkhip.firestoreapp.domain.model.HealthData
import com.ermolaev_arkhip.firestoreapp.domain.repository.HealthDataRepository
import com.ermolaev_arkhip.firestoreapp.domain.utils.GetState
import com.ermolaev_arkhip.firestoreapp.domain.utils.LoadState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: HealthDataRepository
) : ViewModel() {

    private val _getList = MutableLiveData<GetState>()
    val getList: LiveData<GetState> get() = _getList

    private val _loadData = MutableLiveData<LoadState>()
    val loadData: LiveData<LoadState> get() = _loadData

    init {
        getListOfHealthData()
    }

    fun getListOfHealthData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getListOfHealthData().collect {
                _getList.postValue(it)
            }
        }
    }

    fun saveHealthData(healthData: HealthData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveHealthData(healthData).collect {
                _loadData.postValue(it)
            }
        }
    }
}