package com.example.uaspam.ui.view.monitoring

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.uaspam.data.DaftarKandang
import com.example.uaspam.data.DaftarPetugas
import com.example.uaspam.ui.customwidget.DynamicSelected
import com.example.uaspam.ui.viewmodel.monitoring.FormErrorStatemtr
import com.example.uaspam.ui.viewmodel.monitoring.InsertMUiEvent
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertMUiEvent: InsertMUiEvent,
    modifier: Modifier = Modifier,
    errorState: FormErrorStatemtr = FormErrorStatemtr(),
    onValueChange: (InsertMUiEvent) -> Unit = {},
    enabled: Boolean = true,
    onDismiss: () -> Unit,
) {
    var pilihPetugas by remember { mutableStateOf("") }
    var pilihKandang by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }

    LaunchedEffect(insertMUiEvent.Id_petugas) {
        if (insertMUiEvent.Id_petugas.isNotEmpty()) {
            pilihPetugas = insertMUiEvent.Id_petugas
        }
    }

    LaunchedEffect(insertMUiEvent.Id_kandang) {
        if (insertMUiEvent.Id_kandang.isNotEmpty()) {
            pilihKandang = insertMUiEvent.Id_kandang
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = "ID Monitoring Terisi Otomatis",
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        DynamicSelected(
            selectedValue = pilihKandang,
            options = DaftarKandang.ListKandang(),
            label = "Kandang",
            onValueChangedEvent = {
                pilihKandang = it
                onValueChange(insertMUiEvent.copy(Id_kandang = it))
            },
        )

        DynamicSelected(
            selectedValue = pilihPetugas,
            options = DaftarPetugas.ListPetugas(),
            label = "Petugas",
            onValueChangedEvent = {
                pilihPetugas = it
                onValueChange(insertMUiEvent.copy(Id_petugas = it))
            }
        )

        Button(onClick = { showDatePicker = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF018786),
                contentColor = Color.White
            )
            ) {
            Text("Pilih Jadwal")
        }

        // tampil saat shodatepickernya true
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                    onDismiss()
                },
                confirmButton = {
                    TextButton(onClick = {
                        selectedDateMillis = datePickerState.selectedDateMillis
                        showDatePicker = false
                        showTimePicker = true
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                        onDismiss()
                    }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (showTimePicker) {
            TimePickerDialog(
                LocalContext.current,
                { _, hour, minute ->
                    selectedHour = hour
                    selectedMinute = minute

                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = selectedDateMillis ?: System.currentTimeMillis()
                    calendar.set(Calendar.HOUR_OF_DAY, hour)
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.SECOND, 0)

                    val combinedDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                        .format(calendar.time)

                    onValueChange(insertMUiEvent.copy(Tanggal_monitoring = combinedDateTime))

                    showTimePicker = false
                },
                selectedHour,
                selectedMinute,
                true
            ).show()
        }

        if (insertMUiEvent.Tanggal_monitoring.isNotEmpty()) {
            Text(text = "Pilih tanggal dan Jam: ${insertMUiEvent.Tanggal_monitoring}", style = MaterialTheme.typography.bodyMedium)
        } else {
            Text(text = "Belum memilih tanggal dan jam", style = MaterialTheme.typography.bodyMedium)
        }

        OutlinedTextField(
            value = insertMUiEvent.Hewan_sakit.toString(),
            onValueChange = {
                val newValue = it.toIntOrNull()
                if (newValue != null) {
                    onValueChange(insertMUiEvent.copy(Hewan_sakit = newValue))
                    updateStatus(insertMUiEvent.copy(Hewan_sakit = newValue), onValueChange)
                }
            },
            label = { Text("Hewan Sakit") },
            isError = errorState.Hewan_sakit != null,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = ""
                )
            }
        )

        OutlinedTextField(
            value = insertMUiEvent.Hewan_sehat.toString(),
            onValueChange = {
                val newValue = it.toIntOrNull()
                if (newValue != null) {
                    onValueChange(insertMUiEvent.copy(Hewan_sehat = newValue))
                    updateStatus(insertMUiEvent.copy(Hewan_sehat = newValue), onValueChange)
                }
            },
            label = { Text("Hewan Sehat") },
            isError = errorState.Hewan_sehat != null,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = ""
                )
            }
        )
        Text(
            text = "Status: ${insertMUiEvent.Status}",
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun updateStatus(insertMUiEvent: InsertMUiEvent, onValueChange: (InsertMUiEvent) -> Unit) {
    val status = when {
        insertMUiEvent.Hewan_sakit < 10 -> "Aman"
        insertMUiEvent.Hewan_sakit < 50 -> "Waspada"
        insertMUiEvent.Hewan_sakit > 51 -> "Kritis"
        else -> "Aman"
    }
    onValueChange(insertMUiEvent.copy(Status = status))
}



