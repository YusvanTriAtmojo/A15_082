package com.example.uaspam.ui.viewmodel.kandang


import com.example.uaspam.model.Kandang

sealed class HomeKUiState {
    data class Success(val kandang: List<Kandang>): HomeKUiState()
    object Error : HomeKUiState()
    object Loading : HomeKUiState()
}
