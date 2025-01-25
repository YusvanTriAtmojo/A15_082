package com.example.uaspam.ui.view.hewan


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.data.DaftarZona
import com.example.uaspam.data.TipePakan
import com.example.uaspam.ui.customwidget.CostumeTopAppBar
import com.example.uaspam.ui.customwidget.DynamicRadioButton
import com.example.uaspam.ui.navigation.DestinasiInsertHewan
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.hewan.FormErrorStatehwn
import com.example.uaspam.ui.viewmodel.hewan.InsertHewanViewModel
import com.example.uaspam.ui.viewmodel.hewan.InsertUiEvent
import com.example.uaspam.ui.viewmodel.hewan.InsertUiState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryHwnScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertHewanViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertHewan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            additionalText = "Harap Isi Semua Data !",
            insertUiState = viewModel.uiState,
            onHewanValueChange = viewModel::InsertHwnState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertHwn()
                    navigateBack()
                }
            },
            onCancelClick = {
                navigateBack()
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onHewanValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    additionalText: String = "",
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        item {
            Text(
                text = additionalText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Red
            )
        }
        item {
            FormInput(
                insertUiEvent = insertUiState.insertUiEvent,
                onValueChange = onHewanValueChange,
                errorState = insertUiState.isEntryValid,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), // Memberikan jarak antara tombol
                modifier = Modifier.fillMaxWidth().padding(16.dp), // Menambahkan padding sekitar row
                verticalAlignment = Alignment.CenterVertically ) {
                Button(
                    onClick = onCancelClick,
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF016D47),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Cancel")
                }
                Button(
                    onClick = onSaveClick,
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF016D47),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Simpan")
                }
            }
        }
    }
}


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
            text = "ID Terisi Otomatis",
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
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
        Text(
            text = "Tipe Pakan"
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
        Text(
            text = "Zona Wilayah"
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