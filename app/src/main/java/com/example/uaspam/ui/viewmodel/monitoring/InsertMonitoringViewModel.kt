package com.example.uaspam.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Monitoring
import com.example.uaspam.repository.MonitoringRepository
import kotlinx.coroutines.launch

class InsertMonitoringViewModel(private val mtr: MonitoringRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertMUiState())
        private set

    fun InsertMtrState(insertMUiEvent: InsertMUiEvent) {
        updateStatus()

        uiState = uiState.copy(
            insertMUiEvent = insertMUiEvent.copy(Status = uiState.insertMUiEvent.Status)
        )
    }

    private fun updateStatus() {
        val event = uiState.insertMUiEvent
        val totalPopulasi = event.Hewan_sakit + event.Hewan_sehat
        val persentaseSakit = if (totalPopulasi > 0) (event.Hewan_sakit.toDouble() / totalPopulasi) * 100 else 0.0

        val status = when {
            persentaseSakit < 10 -> "Aman"
            persentaseSakit in 11.0..50.0 -> "Waspada"
            persentaseSakit > 51 -> "Kritis"
            else -> "Aman"
        }

        uiState = uiState.copy(
            insertMUiEvent = uiState.insertMUiEvent.copy(Status = status)
        )
    }

    private fun validateFields(): Boolean {
        val event = uiState.insertMUiEvent
        val errorState = FormErrorStatemtr(
            Id_monitoring = if (event.Id_monitoring.isNotEmpty()) null else "ID tidak boleh kosong",
            Id_kandang = if (event.Id_kandang.isNotEmpty()) null else "ID kandang tidak boleh kosong",
            Id_petugas = if (event.Id_petugas.isNotEmpty()) null else "ID petugas tidak boleh kosong",
            Hewan_sakit = if (event.Hewan_sakit >= 0) null else "Hewan Sakit tidak boleh kosong",
            Hewan_sehat = if (event.Hewan_sehat >= 0) null else "Hewan Sehat tidak boleh kosong",
            Status = if (event.Status.isNotEmpty()) null else "Status tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun insertMtr() {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    mtr.insertMonitoring(uiState.insertMUiEvent.toMtr())
                    uiState = uiState.copy(snackBarMessage = "Data berhasil disimpan")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

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
