package com.example.uaspam.ui.viewmodel.monitoring

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Monitoring
import com.example.uaspam.model.MonitoringFull
import com.example.uaspam.repository.MonitoringRepository
import com.example.uaspam.ui.navigation.DestinasiUpdateMonitoring
import kotlinx.coroutines.launch

class UpdateMonitoringViewModel(
    private val repositoryMtr: MonitoringRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var updateMtrUIState by mutableStateOf(InsertMUiState())
        private set

    private val idm: String = checkNotNull(savedStateHandle[DestinasiUpdateMonitoring.IDM])

    init {
        viewModelScope.launch {
            val monitoringFull = repositoryMtr.getMonitoringbyId(idm).data4
            updateMtrUIState = monitoringFull.toMonitoring().toUiStateMtr()
        }
    }

    fun updateMtrState(insertMUiEvent: InsertMUiEvent) {
        updateStatus()

        updateMtrUIState = updateMtrUIState.copy(
            insertMUiEvent = insertMUiEvent.copy(Status = updateMtrUIState.insertMUiEvent.Status)
        )
    }

    private fun updateStatus() {
        val event = updateMtrUIState.insertMUiEvent
        val totalPopulasi = event.Hewan_sakit + event.Hewan_sehat
        val persentaseSakit = if (totalPopulasi > 0) (event.Hewan_sakit.toDouble() / totalPopulasi) * 100 else 0.0

        val status = when {
            persentaseSakit < 10 -> "Aman"
            persentaseSakit in 11.0..50.0 -> "Waspada"
            persentaseSakit > 51 -> "Kritis"
            else -> "Aman"
        }

        updateMtrUIState = updateMtrUIState.copy(
            insertMUiEvent = updateMtrUIState.insertMUiEvent.copy(Status = status)
        )
    }

    private fun validateFields(): Boolean {
        val event = updateMtrUIState.insertMUiEvent
        val errorState = FormErrorStatemtr(
            Id_kandang = if (event.Id_kandang.isNotEmpty()) null else "ID kandang tidak boleh kosong",
            Id_petugas = if (event.Id_petugas.isNotEmpty()) null else "ID petugas tidak boleh kosong",
            Hewan_sakit = if (event.Hewan_sakit >= 0) null else "Hewan Sakit tidak boleh kosong",
            Hewan_sehat = if (event.Hewan_sehat >= 0) null else "Hewan Sehat tidak boleh kosong",
            Status = if (event.Status.isNotEmpty()) null else "Status tidak boleh kosong",
        )
        updateMtrUIState = updateMtrUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    suspend fun updateMtr() {
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMtr.updateMonitoring(idm, updateMtrUIState.insertMUiEvent.toMtr())
                    updateMtrUIState = updateMtrUIState.copy(snackBarMessage = "Data berhasil disimpan")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            updateMtrUIState = updateMtrUIState.copy(
                snackBarMessage = "Update tidak valid. Periksa kembali data Anda."
            )
        }
    }
    fun resetSnackBarMessage() {
        updateMtrUIState = updateMtrUIState.copy(snackBarMessage = null)
    }
}

fun MonitoringFull.toMonitoring(): Monitoring {
    return Monitoring(
        Id_monitoring = this.Id_monitoring,
        Id_petugas = this.Id_petugas,
        Id_kandang = this.Id_kandang,
        Tanggal_monitoring = this.Tanggal_monitoring,
        Hewan_sakit = this.Hewan_sakit,
        Hewan_sehat = this.Hewan_sehat,
        Status = this.Status
    )
}