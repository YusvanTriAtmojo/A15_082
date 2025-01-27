package com.example.uaspam.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.MonitoringDetailResponse
import com.example.uaspam.repository.MonitoringRepository
import com.example.uaspam.ui.navigation.DestinasiDetailMonitoring
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailMUiState {
    data class Success(val monitoring: MonitoringDetailResponse): DetailMUiState()
    object Error : DetailMUiState()
    object Loading : DetailMUiState()
}

class DetailMonitoringViewModel(
    savedStateHandle: SavedStateHandle,
    private val mtr: MonitoringRepository
) : ViewModel() {
    var detailMUiState: DetailMUiState by mutableStateOf(DetailMUiState.Loading)
        private set

    private val id: String = checkNotNull(savedStateHandle[DestinasiDetailMonitoring.IDM])

    init {
        getMonitoringById()
    }

    fun getMonitoringById(){
        viewModelScope.launch {
            detailMUiState = DetailMUiState.Loading
            detailMUiState = try {
                val monitoring = mtr.getMonitoringbyId(id)
                DetailMUiState.Success(monitoring)
            } catch (e: IOException){
                DetailMUiState.Error
            }
        }
    }
    fun deleteMtr(id: String){
        viewModelScope.launch {
            try {
                mtr.deleteMonitoring(id)
            } catch (e: IOException){
                DetailMUiState.Error
            } catch (e: HttpException) {
                DetailMUiState.Error
            }
        }
    }
}