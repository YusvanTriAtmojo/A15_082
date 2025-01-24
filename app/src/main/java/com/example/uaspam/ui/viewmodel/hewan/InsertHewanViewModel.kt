package com.example.uaspam.ui.viewmodel.hewan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Hewan
import com.example.uaspam.repository.HewanRepository
import kotlinx.coroutines.launch

class InsertHewanViewModel(
    private val hwn: HewanRepository
): ViewModel()
{
    var uiState by mutableStateOf(InsertUiState())
        private set

    fun InsertHwnState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }
    private fun validateFields(): Boolean {
        val event = uiState.insertUiEvent
        val errorState = FormErrorStatehwn(
            Nama_hewan = if (event.Nama_hewan.isNotEmpty()) null else "Nama tidak boleh kosong",
            Tipe_pakan = if (event.Tipe_pakan.isNotEmpty()) null else "Tipe pakan tidak boleh kosong",
            populasi = if (event.populasi >= 0) null else "Populasi tidak boleh kosong",
            Zona_wilayah = if (event.Zona_wilayah.isNotEmpty()) null else "Zona wilayah Suplier tidak boleh kosong"
        )

        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun insertHwn(){
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    hwn.insertHewan(uiState.insertUiEvent.toHwn())
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


data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent(),
    var isEntryValid: FormErrorStatehwn = FormErrorStatehwn(),
    val snackBarMessage: String? = null,
)

data class FormErrorStatehwn(
    val Id_hewan: String? = null,
    val Nama_hewan: String? = null,
    val Tipe_pakan: String? = null,
    val populasi: String? = null,
    val Zona_wilayah: String? = null,
) {
    fun isValid(): Boolean {
        return Id_hewan == null && Nama_hewan == null && Tipe_pakan == null &&
                populasi  == null && Zona_wilayah == null
    }
}

data class InsertUiEvent(
    val Id_hewan: String = "",
    val Nama_hewan: String = "",
    val Tipe_pakan: String = "",
    val populasi: Int = 0,
    val Zona_wilayah: String = "",
)

fun InsertUiEvent.toHwn(): Hewan = Hewan(
    Id_hewan = Id_hewan,
    Nama_hewan = Nama_hewan,
    Tipe_pakan = Tipe_pakan,
    populasi = populasi,
    Zona_wilayah = Zona_wilayah,
)

fun Hewan.toUiStateHwn(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun Hewan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    Id_hewan = Id_hewan,
    Nama_hewan = Nama_hewan,
    Tipe_pakan = Tipe_pakan,
    populasi = populasi,
    Zona_wilayah = Zona_wilayah,
)

