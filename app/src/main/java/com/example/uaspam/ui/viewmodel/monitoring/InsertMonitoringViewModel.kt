package com.example.uaspam.ui.viewmodel.monitoring

import com.example.uaspam.model.Monitoring

data class InsertMUiState(
    val insertMUiEvent: InsertMUiEvent = InsertMUiEvent(),
    var isEntryValid: FormErrorStatemtr = FormErrorStatemtr(),
    val snackBarMessage: String? = null,
)

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

fun Monitoring.toUiStateMtr(): InsertMUiState = InsertMUiState(
    insertMUiEvent = toInsertMUiEvent()
)

fun InsertMUiEvent.toMtr(): Monitoring = Monitoring(
    Id_monitoring = Id_monitoring,
    Id_kandang = Id_kandang,
    Id_petugas = Id_petugas,
    Tanggal_monitoring = Tanggal_monitoring,
    Hewan_sakit = Hewan_sakit,
    Hewan_sehat = Hewan_sehat,
    Status = Status
)

fun Monitoring.toInsertMUiEvent(): InsertMUiEvent = InsertMUiEvent(
    Id_monitoring = Id_monitoring,
    Id_kandang = Id_kandang,
    Id_petugas = Id_petugas,
    Tanggal_monitoring = Tanggal_monitoring,
    Hewan_sakit = Hewan_sakit,
    Hewan_sehat = Hewan_sehat,
    Status = Status
)
