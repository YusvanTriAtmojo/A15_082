package com.example.uaspam.ui.viewmodel.kandang


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kandang
import com.example.uaspam.repository.KandangRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeKUiState {
    data class Success(val kandang: List<Kandang>): HomeKUiState()
    object Error : HomeKUiState()
    object Loading : HomeKUiState()
}

class HomeKandangViewModel(private val kdg: KandangRepository) : ViewModel() {
    var kdgUiState: HomeKUiState by mutableStateOf(HomeKUiState.Loading)
        private set

    init {
        getKdg()
    }

    fun getKdg(){
        viewModelScope.launch {
            kdgUiState = HomeKUiState.Loading
            kdgUiState = try {
                HomeKUiState.Success(kdg.getKandang().data2)
            } catch (e: IOException){
                HomeKUiState.Error
            } catch (e: HttpException){
                HomeKUiState.Error
            }
        }
    }
}
