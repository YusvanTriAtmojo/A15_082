package com.example.uaspam.ui.view.kandang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
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
import com.example.uaspam.data.DaftarHewan
import com.example.uaspam.ui.customwidget.DynamicSelectedTextField
import com.example.uaspam.ui.viewmodel.kandang.FormErrorStatekdg
import com.example.uaspam.ui.viewmodel.kandang.InsertKUiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertKUiEvent: InsertKUiEvent,
    modifier: Modifier = Modifier,
    errorState: FormErrorStatekdg = FormErrorStatekdg(),
    onValueChange: (InsertKUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    var pilihHewan by remember {
        mutableStateOf("")
    }

    LaunchedEffect(insertKUiEvent.Id_hewan) {
        if (insertKUiEvent.Id_hewan.isNotEmpty()) {
            pilihHewan = insertKUiEvent.Id_hewan
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        OutlinedTextField(
            value = insertKUiEvent.Id_kandang,
            onValueChange = { onValueChange(insertKUiEvent.copy(Id_kandang = it))},
            label = { Text("Id Kandang") },
            isError = errorState.Id_kandang != null,
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
            text = errorState.Id_kandang ?: "",
            color = Color.Red
        )
        DynamicSelectedTextField(
            selectedValue = pilihHewan,
            options = DaftarHewan.ListHewan(),
            label = "Id Hewan",
            onValueChangedEvent = {
                pilihHewan = it
                onValueChange(insertKUiEvent.copy(Id_hewan = it))
            },
        )
        Text(
            text = errorState.Id_hewan?: "",
            color = Color.Red
        )
        OutlinedTextField(
            value = insertKUiEvent.kapasitas.toString(),
            onValueChange = {
                val newValue = it.toIntOrNull()
                if (newValue != null) {
                    onValueChange(insertKUiEvent.copy(kapasitas = newValue))
                }
            },
            label = { Text("Kapasitas") },
            isError = errorState.kapasitas != null,
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
            text = errorState.kapasitas?: "",
            color = Color.Red
        )
        OutlinedTextField(
            value = insertKUiEvent.lokasi,
            onValueChange = { onValueChange(insertKUiEvent.copy(lokasi = it))},
            label = { Text("Lokasi") },
            isError = errorState.lokasi != null,
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
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = ""
                )
            }
        )
        Text(
            text = errorState.lokasi ?: "",
            color = Color.Red
        )
    }
}