package com.example.uaspam.ui.viewmodel.petugas

import com.example.uaspam.model.Petugas


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


