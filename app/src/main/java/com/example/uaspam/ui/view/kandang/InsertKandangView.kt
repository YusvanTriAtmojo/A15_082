package com.example.uaspam.ui.view.kandang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
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
import com.example.uaspam.data.DaftarHewan
import com.example.uaspam.ui.customwidget.CostumeTopAppBar
import com.example.uaspam.ui.customwidget.DynamicSelectedTextField
import com.example.uaspam.ui.navigation.DestinasiInsertKandang
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.kandang.FormErrorStatekdg
import com.example.uaspam.ui.viewmodel.kandang.InsertKUiEvent
import com.example.uaspam.ui.viewmodel.kandang.InsertKUiState
import com.example.uaspam.ui.viewmodel.kandang.InsertKandangViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKdgScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKandangViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
                title = DestinasiInsertKandang.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            additionalText = "Harap Isi Semua Data !",
            insertKUiState = viewModel.uiState,
            onKandangValueChange = viewModel::InsertKdgState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKdg()
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
    insertKUiState: InsertKUiState,
    onKandangValueChange: (InsertKUiEvent) -> Unit,
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
                insertKUiEvent = insertKUiState.insertKUiEvent,
                onValueChange = onKandangValueChange,
                errorState = insertKUiState.isEntryValid,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Row (horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth().padding(16.dp),
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