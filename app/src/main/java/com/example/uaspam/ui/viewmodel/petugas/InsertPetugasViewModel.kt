package com.example.uaspam.ui.viewmodel.petugas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Petugas
import com.example.uaspam.repository.PetugasRepository
import kotlinx.coroutines.launch

class InsertPetugasViewModel(private val ptg: PetugasRepository): ViewModel() {
    var uiState by mutableStateOf(InsertPUiState())
        private set

    fun InsertPtgState(insertPUiEvent: InsertPUiEvent){
        uiState = InsertPUiState(insertPUiEvent = insertPUiEvent)
    }
    private fun validateFields(): Boolean {
        val event = uiState.insertPUiEvent
        val errorState = FormErrorStateptg(
            Id_petugas = if (event.Id_petugas.isNotEmpty()) null else "ID tidak boleh kosong",
            Nama_petugas = if (event.Nama_petugas.isNotEmpty()) null else "Nama tidak boleh kosong",
            jabatan = if (event.jabatan.isNotEmpty()) null else "Jabatan tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun insertPtg(){
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    ptg.insertPetugas(uiState.insertPUiEvent.toPtg())
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

data class InsertPUiState(
    val insertPUiEvent: InsertPUiEvent = InsertPUiEvent(),
    var isEntryValid: FormErrorStateptg = FormErrorStateptg(),
    val snackBarMessage: String? = null,
)

data class FormErrorStateptg(
    val Id_petugas: String? = null,
    val Nama_petugas: String? = null,
    val jabatan: String? = null,
) {
    fun isValid(): Boolean {
        return Id_petugas == null && Nama_petugas == null && jabatan == null
    }
}

data class InsertPUiEvent(
    val Id_petugas: String = "",
    val Nama_petugas: String = "",
    val jabatan: String = "",
)

fun InsertPUiEvent.toPtg(): Petugas = Petugas(
    Id_petugas = Id_petugas,
    Nama_petugas = Nama_petugas,
    jabatan = jabatan,
)

fun Petugas.toUiStatePtg(): InsertPUiState = InsertPUiState(
    insertPUiEvent = toInsertPUiEvent()
)

fun Petugas.toInsertPUiEvent(): InsertPUiEvent = InsertPUiEvent(
    Id_petugas = Id_petugas,
    Nama_petugas = Nama_petugas,
    jabatan = jabatan,
)


