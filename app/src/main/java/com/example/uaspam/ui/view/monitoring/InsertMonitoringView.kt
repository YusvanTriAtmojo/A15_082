package com.example.uaspam.ui.view.monitoring

import com.example.uaspam.ui.viewmodel.monitoring.InsertMUiEvent

fun updateStatus(insertMUiEvent: InsertMUiEvent, onValueChange: (InsertMUiEvent) -> Unit) {
    val status = when {
        insertMUiEvent.Hewan_sakit < 10 -> "Aman"
        insertMUiEvent.Hewan_sakit < 50 -> "Waspada"
        insertMUiEvent.Hewan_sakit > 51 -> "Kritis"
        else -> "Aman"
    }
    onValueChange(insertMUiEvent.copy(Status = status))
}



