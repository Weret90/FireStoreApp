package com.ermolaev_arkhip.firestoreapp.domain.utils

sealed class LoadState {
    data class Error(val throwable: Throwable): LoadState()
    object Success: LoadState()
}