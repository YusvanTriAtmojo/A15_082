package com.example.uaspam.ui.viewmodel.kandang

import com.example.uaspam.model.KandangDetailResponse

sealed class DetailKUiState {
    data class Success(val kandang: KandangDetailResponse): DetailKUiState()
    object Error : DetailKUiState()
    object Loading : DetailKUiState()
}
