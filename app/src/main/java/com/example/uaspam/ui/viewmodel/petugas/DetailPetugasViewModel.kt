package com.example.uaspam.ui.viewmodel.petugas

import com.example.uaspam.model.PetugasDetailResponse

sealed class DetailPUiState {
    data class Success(val petugas: PetugasDetailResponse): DetailPUiState()
    object Error : DetailPUiState()
    object Loading : DetailPUiState()
}
