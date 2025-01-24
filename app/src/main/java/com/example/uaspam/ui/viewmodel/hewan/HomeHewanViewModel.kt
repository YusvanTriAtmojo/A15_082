package com.example.uaspam.ui.viewmodel.hewan


import com.example.uaspam.model.Hewan

sealed class HomeHUiState {
    data class Success(val hewan: List<Hewan>): HomeHUiState()
    object Error : HomeHUiState()
    object Loading : HomeHUiState()
}

