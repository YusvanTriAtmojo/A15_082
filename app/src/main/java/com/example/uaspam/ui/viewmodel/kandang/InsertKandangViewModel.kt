package com.example.uaspam.ui.viewmodel.kandang

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
