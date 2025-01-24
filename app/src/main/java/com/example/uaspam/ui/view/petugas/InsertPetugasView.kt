package com.example.uaspam.ui.view.petugas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.uaspam.data.Jabatan
import com.example.uaspam.ui.customwidget.DynamicSelectedTextField
import com.example.uaspam.ui.viewmodel.petugas.FormErrorStateptg
import com.example.uaspam.ui.viewmodel.petugas.InsertPUiEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertPUiEvent: InsertPUiEvent,
    modifier: Modifier = Modifier,
    errorState: FormErrorStateptg = FormErrorStateptg(),
    onValueChange: (InsertPUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    var chosenDropdown by remember {
        mutableStateOf(
            ""
        )
    }

    LaunchedEffect(insertPUiEvent.jabatan) {
        if (insertPUiEvent.jabatan.isNotEmpty()) {
            chosenDropdown = insertPUiEvent.jabatan
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        OutlinedTextField(
            value = insertPUiEvent.Id_petugas,
            onValueChange = { onValueChange(insertPUiEvent.copy(Id_petugas = it))},
            label = { Text("Id Petugas") },
            isError = errorState.Id_petugas != null,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = ""
                )
            }
        )
        Text(
            text = errorState.Id_petugas ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            value = insertPUiEvent.Nama_petugas,
            onValueChange = { onValueChange(insertPUiEvent.copy(Nama_petugas = it))},
            label = { Text("Nama") },
            isError = errorState.Nama_petugas != null,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = RoundedCornerShape(50.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = ""
                )
            }
        )
        Text(
            text = errorState.Nama_petugas ?: "",
            color = Color.Red
        )
        DynamicSelectedTextField(
            selectedValue = chosenDropdown,
            options = Jabatan.listJabatan,
            label = "Jabatan",
            onValueChangedEvent = {
                chosenDropdown = it
                onValueChange(insertPUiEvent.copy(jabatan = it))
            },
        )
        Text(
            text = errorState.jabatan ?: "",
            color = Color.Red
        )
    }
}