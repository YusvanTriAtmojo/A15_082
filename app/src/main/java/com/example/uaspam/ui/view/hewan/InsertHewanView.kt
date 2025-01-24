package com.example.uaspam.ui.view.hewan


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
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
import com.example.uaspam.data.DaftarZona
import com.example.uaspam.data.TipePakan
import com.example.uaspam.ui.customwidget.DynamicRadioButton
import com.example.uaspam.ui.viewmodel.hewan.FormErrorStatehwn
import com.example.uaspam.ui.viewmodel.hewan.InsertUiEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    errorState: FormErrorStatehwn = FormErrorStatehwn(),
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    var pilihPakan by remember {
        mutableStateOf("")
    }

    var pilihZona by remember {
        mutableStateOf("")
    }

    LaunchedEffect(insertUiEvent.Tipe_pakan) {
        if (insertUiEvent.Tipe_pakan.isNotEmpty()) {
            pilihPakan = insertUiEvent.Tipe_pakan
        }
    }

    LaunchedEffect(insertUiEvent.Zona_wilayah) {
        if (insertUiEvent.Zona_wilayah.isNotEmpty()) {
            pilihZona = insertUiEvent.Zona_wilayah
        }
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = errorState.Id_hewan ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            value = insertUiEvent.Nama_hewan,
            onValueChange = { onValueChange(insertUiEvent.copy(Nama_hewan = it))},
            label = { Text("Nama") },
            isError = errorState.Nama_hewan != null,
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
            text = errorState.Nama_hewan ?: "",
            color = Color.Red
        )
        DynamicRadioButton(
            options = TipePakan.listPakan,
            selectedOption = pilihPakan,
            onOptionSelected = {
                pilihPakan = it
                onValueChange(insertUiEvent.copy(Tipe_pakan = it))
            }
        )

        Text(
            text = errorState.Tipe_pakan ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            value = insertUiEvent.populasi.toString(),
            onValueChange = {
                val newValue = it.toIntOrNull()
                if (newValue != null) {
                    onValueChange(insertUiEvent.copy(populasi = newValue))
                }
            },
            label = { Text("Populasi") },
            isError = errorState.populasi != null,
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
            text = errorState.populasi?: "",
            color = Color.Red
        )
        DynamicRadioButton(
            options = DaftarZona.listZona,
            selectedOption = pilihZona,
            onOptionSelected = {
                pilihZona = it
                onValueChange(insertUiEvent.copy(Zona_wilayah = it))
            }
        )
        Text(
            text = errorState.Zona_wilayah ?: "",
            color = Color.Red
        )
    }
}