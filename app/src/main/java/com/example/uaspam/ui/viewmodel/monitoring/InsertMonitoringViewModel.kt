package com.example.uaspam.ui.viewmodel.monitoring

data class FormErrorStatemtr(
    val Id_monitoring: String? = null,
    val Id_kandang: String? = null,
    val Id_petugas: String? = null,
    val Hewan_sakit: String? = null,
    val Hewan_sehat: String? = null,
    val Status: String? = null,
) {
    fun isValid(): Boolean {
        return Id_monitoring == null && Id_kandang == null && Id_petugas == null && Hewan_sakit == null && Hewan_sehat == null
                && Status == null
    }
}

data class InsertMUiEvent(
    val Id_monitoring: String = "",
    val Id_petugas: String = "",
    val Id_kandang: String = "",
    val Tanggal_monitoring: String = "",
    val Hewan_sakit: Int = 0,
    val Hewan_sehat: Int = 0,
    val Status: String = ""
)
