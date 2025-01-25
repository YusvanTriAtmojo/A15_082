package com.example.uaspam.ui.viewmodel.kandang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Kandang
import com.example.uaspam.repository.KandangRepository
import kotlinx.coroutines.launch

class InsertKandangViewModel(private val kdg: KandangRepository): ViewModel() {
    var uiState by mutableStateOf(InsertKUiState())
        private set

    fun InsertKdgState(insertUiEvent: InsertKUiEvent){
        uiState = InsertKUiState(insertKUiEvent = insertUiEvent)
    }
    private fun validateFields(): Boolean {
        val event = uiState.insertKUiEvent
        val errorState = FormErrorStatekdg(
            Id_hewan = if (event.Id_hewan.isNotEmpty()) null else "ID hewan tidak boleh kosong",
            kapasitas = if (event.kapasitas >= 0) null else "Kapasitas tidak boleh kosong",
            lokasi = if (event.lokasi.isNotEmpty()) null else "Lokasi tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun insertKdg(){
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    kdg.insertKandang(uiState.insertKUiEvent.toKdg())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan")
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


data class InsertKUiState(
    val insertKUiEvent: InsertKUiEvent = InsertKUiEvent(),
    var isEntryValid: FormErrorStatekdg = FormErrorStatekdg(),
    val snackBarMessage: String? = null,
)

data class FormErrorStatekdg(
    val Id_kandang: String? = null,
    val Id_hewan: String? = null,
    val kapasitas: String? = null,
    val lokasi: String? = null,
) {
    fun isValid(): Boolean {
        return Id_kandang == null && Id_hewan == null && kapasitas == null &&
                lokasi == null
    }
}

data class InsertKUiEvent(
    val Id_kandang: String = "",
    val Id_hewan: String = "",
    val kapasitas: Int = 0,
    val lokasi: String = "",
)

fun InsertKUiEvent.toKdg(): Kandang = Kandang(
    Id_kandang = Id_kandang,
    Id_hewan = Id_hewan,
    kapasitas = kapasitas,
    lokasi = lokasi,
)

fun Kandang.toInsertKUiEvent(): InsertKUiEvent = InsertKUiEvent(
    Id_kandang = Id_kandang,
    Id_hewan = Id_hewan,
    kapasitas = kapasitas,
    lokasi = lokasi,
)

fun Kandang.toUiStateKdg(): InsertKUiState = InsertKUiState(
    insertKUiEvent = toInsertKUiEvent()
)



