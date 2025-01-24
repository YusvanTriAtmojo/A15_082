package com.example.uaspam.ui.viewmodel.petugas


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
