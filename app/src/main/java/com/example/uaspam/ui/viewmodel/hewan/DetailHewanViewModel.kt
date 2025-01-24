package com.example.uaspam.ui.viewmodel.hewan


import com.example.uaspam.model.HewanDetailResponse

sealed class DetailHUiState {
    data class Success(val hewan: HewanDetailResponse): DetailHUiState()
    object Error : DetailHUiState()
    object Loading : DetailHUiState()
}
