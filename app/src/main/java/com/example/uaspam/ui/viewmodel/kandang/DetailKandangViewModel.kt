package com.example.uaspam.ui.viewmodel.kandang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.KandangDetailResponse
import com.example.uaspam.repository.KandangRepository
import com.example.uaspam.ui.navigation.DestinasiDetailKandang
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailKUiState {
    data class Success(val kandang: KandangDetailResponse): DetailKUiState()
    object Error : DetailKUiState()
    object Loading : DetailKUiState()
}

class DetailKandangViewModel(
    savedStateHandle: SavedStateHandle,
    private val kdg: KandangRepository
) : ViewModel() {
    var detailKUiState: DetailKUiState by mutableStateOf(DetailKUiState.Loading)
        private set

    private val id: String = checkNotNull(savedStateHandle[DestinasiDetailKandang.IDK])

    init {
        getKandangById()
    }

    fun getKandangById(){
        viewModelScope.launch {
            detailKUiState = DetailKUiState.Loading
            detailKUiState = try {
                val kandang = kdg.getKandangbyId(id)
                DetailKUiState.Success(kandang)
            } catch (e: IOException){
                DetailKUiState.Error
            }
        }
    }
}
