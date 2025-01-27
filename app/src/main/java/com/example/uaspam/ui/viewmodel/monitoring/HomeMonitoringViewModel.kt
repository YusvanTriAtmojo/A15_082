package com.example.uaspam.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Monitoring
import com.example.uaspam.repository.MonitoringRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeMUiState {
    data class Success(val monitoring: List<Monitoring>): HomeMUiState()
    object Error : HomeMUiState()
    object Loading : HomeMUiState()
}

class HomeMonitoringViewModel(private val mtr: MonitoringRepository) : ViewModel() {
    var mtrUiState: HomeMUiState by mutableStateOf(HomeMUiState.Loading)
        private set

    init {
        getMtr()
    }

    fun getMtr(){
        viewModelScope.launch {
            mtrUiState = HomeMUiState.Loading
            mtrUiState = try {
                HomeMUiState.Success(mtr.getMonitoring().data4)
            } catch (e: IOException){
                HomeMUiState.Error
            } catch (e: HttpException){
                HomeMUiState.Error
            }
        }
    }
}
