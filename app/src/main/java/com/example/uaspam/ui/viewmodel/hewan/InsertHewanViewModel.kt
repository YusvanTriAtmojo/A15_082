package com.example.uaspam.ui.viewmodel.hewan

import com.example.uaspam.model.Hewan

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

fun Hewan.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    Id_hewan = Id_hewan,
    Nama_hewan = Nama_hewan,
    Tipe_pakan = Tipe_pakan,
    populasi = populasi,
    Zona_wilayah = Zona_wilayah,
)

