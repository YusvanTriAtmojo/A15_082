package com.example.uaspam.ui.viewmodel.kandang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kandang
import com.example.uaspam.model.KandangHewan
import com.example.uaspam.repository.KandangRepository
import com.example.uaspam.ui.navigation.DestinasiUpdateKandang
import kotlinx.coroutines.launch

class UpdateKandangViewModel(
    private val repositoryKdg: KandangRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var updateKdgUIState by mutableStateOf(InsertKUiState())
        private set

    private val idk: String = checkNotNull(savedStateHandle[DestinasiUpdateKandang.IDK])

    init {
        viewModelScope.launch {
            updateKdgUIState = repositoryKdg.getKandangbyId(idk)
                .data2.toUiStateKdg()
        }
    }

    fun updateState(insertKUiEvent: InsertKUiEvent) {
        updateKdgUIState = InsertKUiState(insertKUiEvent = insertKUiEvent)
    }

    private fun validateFields(): Boolean {
        val event = updateKdgUIState.insertKUiEvent
        val errorState = FormErrorStatekdg(
            Id_hewan = if (event.Id_hewan.isNotEmpty()) null else "Id Hewan tidak boleh kosong",
            kapasitas = if (event.kapasitas >= 0) null else "Kapasitas tidak boleh kosong",
            lokasi = if (event.lokasi.isNotEmpty()) null else "Lokasi tidak boleh kosong",
        )

        updateKdgUIState = updateKdgUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun updateKandang()
    {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryKdg.updateKandang(idk, updateKdgUIState.insertKUiEvent.toKdg())
                    updateKdgUIState = updateKdgUIState.copy(
                        snackBarMessage = "Data berhasil disimpan")
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        } else {
            updateKdgUIState = updateKdgUIState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackBarMessage(){
        updateKdgUIState = updateKdgUIState.copy(snackBarMessage = null)
    }
}

fun KandangHewan.toKandang(): Kandang {
    return Kandang(
        Id_kandang = this.Id_kandang,
        Id_hewan = this.Id_hewan,
        kapasitas = this.kapasitas,
        lokasi = this.lokasi
    )
}


