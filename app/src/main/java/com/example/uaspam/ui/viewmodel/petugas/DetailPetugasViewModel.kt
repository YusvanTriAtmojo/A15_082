package com.example.uaspam.ui.viewmodel.petugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.PetugasDetailResponse
import com.example.uaspam.repository.PetugasRepository
import com.example.uaspam.ui.navigation.DestinasiDetailPetugas
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailPUiState {
    data class Success(val petugas: PetugasDetailResponse): DetailPUiState()
    object Error : DetailPUiState()
    object Loading : DetailPUiState()
}

class DetailPetugasViewModel(
    savedStateHandle: SavedStateHandle,
    private val ptg: PetugasRepository
) : ViewModel() {
    var detailPUiState: DetailPUiState by mutableStateOf(DetailPUiState.Loading)
        private set

    private val id: String = checkNotNull(savedStateHandle[DestinasiDetailPetugas.IDP])

    init {
        getPetugasById()
    }

    fun getPetugasById(){
        viewModelScope.launch {
            detailPUiState = DetailPUiState.Loading
            detailPUiState = try {
                val petugas = ptg.getPetugasbyId(id)
                DetailPUiState.Success(petugas)
            } catch (e: IOException){
                DetailPUiState.Error
            }
        }
    }
}
