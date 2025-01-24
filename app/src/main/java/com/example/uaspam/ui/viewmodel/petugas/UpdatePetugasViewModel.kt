package com.example.uaspam.ui.viewmodel.petugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.PetugasRepository
import com.example.uaspam.ui.navigation.DestinasiUpdatePetugas
import kotlinx.coroutines.launch

class UpdatePetugasViewModel(
    private val repositoryPtg: PetugasRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var updatePtgUIState by mutableStateOf(InsertPUiState())
        private set

    private val id: String = checkNotNull(savedStateHandle[DestinasiUpdatePetugas.IDP])

    init {
        viewModelScope.launch {
            updatePtgUIState = repositoryPtg.getPetugasbyId(id)
                .data3.toUiStatePtg()
        }
    }

    fun updateState(insertPUiEvent: InsertPUiEvent) {
        updatePtgUIState = InsertPUiState(insertPUiEvent = insertPUiEvent)
    }

    private fun validateFields(): Boolean {
        val event = updatePtgUIState.insertPUiEvent
        val errorState = FormErrorStateptg(
            Id_petugas = if (event.Id_petugas.isNotEmpty()) null else "ID tidak boleh kosong",
            Nama_petugas = if (event.Nama_petugas.isNotEmpty()) null else "Nama tidak boleh kosong",
            jabatan = if (event.jabatan.isNotEmpty()) null else "Jabatan tidak boleh kosong",
        )

        updatePtgUIState = updatePtgUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun updatePetugas()
    {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryPtg.updatePetugas(id, updatePtgUIState.insertPUiEvent.toPtg())
                    updatePtgUIState = updatePtgUIState.copy(
                        snackBarMessage = "Data berhasil disimpan")
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        } else {
            updatePtgUIState = updatePtgUIState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackBarMessage(){
        updatePtgUIState = updatePtgUIState.copy(snackBarMessage = null)
    }
}