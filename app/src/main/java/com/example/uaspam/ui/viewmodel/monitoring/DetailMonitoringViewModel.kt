package com.example.uaspam.ui.viewmodel.monitoring

import com.example.uaspam.model.MonitoringDetailResponse

sealed class DetailMUiState {
    data class Success(val monitoring: MonitoringDetailResponse): DetailMUiState()
    object Error : DetailMUiState()
    object Loading : DetailMUiState()
}