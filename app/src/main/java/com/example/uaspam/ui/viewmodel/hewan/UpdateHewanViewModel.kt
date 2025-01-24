package com.example.uaspam.ui.viewmodel.hewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.repository.HewanRepository
import com.example.uaspam.ui.navigation.DestinasiUpdateHewan
import kotlinx.coroutines.launch


class UpdateHewanViewModel(
    private val repositoryHwn: HewanRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var updateHwnUIState by mutableStateOf(InsertUiState())
        private set

    private val id : String = checkNotNull(savedStateHandle[DestinasiUpdateHewan.IDH])

    init {
        viewModelScope.launch {
            updateHwnUIState = repositoryHwn.getHewanbyId(id)
                .data1.toUiStateHwn()
        }
    }

    fun updateState(insertUiEvent: InsertUiEvent) {
        updateHwnUIState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    private fun validateFields(): Boolean {
        val event = updateHwnUIState.insertUiEvent
        val errorState = FormErrorStatehwn(
            Id_hewan = if (event.Id_hewan.isNotEmpty()) null else "ID tidak boleh kosong",
            Nama_hewan = if (event.Nama_hewan.isNotEmpty()) null else "Nama tidak boleh kosong",
            Tipe_pakan = if (event.Tipe_pakan.isNotEmpty()) null else "Tipe pakan tidak boleh kosong",
            populasi = if (event.populasi >= 0) null else "Populasi tidak boleh kosong",
            Zona_wilayah = if (event.Zona_wilayah.isNotEmpty()) null else "Zona wilayah Suplier tidak boleh kosong"
        )

        updateHwnUIState = updateHwnUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun updateHewan()
    {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryHwn.updateHewan(id, updateHwnUIState.insertUiEvent.toHwn())
                    updateHwnUIState = updateHwnUIState.copy(
                        snackBarMessage = "Data berhasil diUpdate")
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        } else {
            updateHwnUIState = updateHwnUIState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackBarMessage(){
        updateHwnUIState = updateHwnUIState.copy(snackBarMessage = null)
    }
}