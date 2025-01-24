package com.example.uaspam.ui.viewmodel.petugas


import com.example.uaspam.model.Petugas

sealed class HomePUiState {
    data class Success(val petugas: List<Petugas>): HomePUiState()
    object Error : HomePUiState()
    object Loading : HomePUiState()
}
