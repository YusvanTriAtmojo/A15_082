package com.example.uaspam.ui.viewmodel.hewan



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