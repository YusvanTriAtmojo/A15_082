package com.example.uaspam.ui.viewmodel.hewan


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Hewan
import com.example.uaspam.repository.HewanRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeHUiState {
    data class Success(val hewan: List<Hewan>): HomeHUiState()
    object Error : HomeHUiState()
    object Loading : HomeHUiState()
}

class HomeHewanViewModel(private val hwn: HewanRepository) : ViewModel() {
    var hwnUiState: HomeHUiState by mutableStateOf(HomeHUiState.Loading)
        private set

    init {
        getHwn()
    }

    fun getHwn(){
        viewModelScope.launch {
            hwnUiState = HomeHUiState.Loading
            hwnUiState = try {
                HomeHUiState.Success(hwn.getHewan().data1)
            } catch (e: IOException){
                HomeHUiState.Error
            } catch (e: HttpException){
                HomeHUiState.Error
            }
        }
    }
}


