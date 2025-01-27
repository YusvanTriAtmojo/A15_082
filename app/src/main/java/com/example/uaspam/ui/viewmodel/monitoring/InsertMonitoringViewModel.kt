package com.example.uaspam.ui.viewmodel.monitoring


data class InsertMUiEvent(
    val Id_monitoring: String = "",
    val Id_petugas: String = "",
    val Id_kandang: String = "",
    val Tanggal_monitoring: String = "",
    val Hewan_sakit: Int = 0,
    val Hewan_sehat: Int = 0,
    val Status: String = ""
)
