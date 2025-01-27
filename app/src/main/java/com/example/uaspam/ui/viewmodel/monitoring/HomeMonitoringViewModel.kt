package com.example.uaspam.ui.viewmodel.monitoring

import com.example.uaspam.model.Monitoring

sealed class HomeMUiState {
    data class Success(val monitoring: List<Monitoring>): HomeMUiState()
    object Error : HomeMUiState()
    object Loading : HomeMUiState()
}
