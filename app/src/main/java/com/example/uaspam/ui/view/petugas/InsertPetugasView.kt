package com.example.uaspam.ui.view.petugas

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
import androidx.compose.material.icons.filled.Info
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
import com.example.uaspam.data.Jabatan
import com.example.uaspam.ui.customwidget.CostumeTopAppBar
import com.example.uaspam.ui.customwidget.DynamicSelectedTextField
import com.example.uaspam.ui.navigation.DestinasiInsertHewan
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.petugas.FormErrorStateptg
import com.example.uaspam.ui.viewmodel.petugas.InsertPUiEvent
import com.example.uaspam.ui.viewmodel.petugas.InsertPUiState
import com.example.uaspam.ui.viewmodel.petugas.InsertPetugasViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPtgScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPetugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
            insertPUiState = viewModel.uiState,
            onPetugasValueChange = viewModel::InsertPtgState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPtg()
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
    insertPUiState: InsertPUiState,
    onPetugasValueChange: (InsertPUiEvent) -> Unit,
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
                insertPUiEvent = insertPUiState.insertPUiEvent,
                onValueChange = onPetugasValueChange,
                errorState = insertPUiState.isEntryValid,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp),
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
        Text(
            text = "ID Terisi Otomatis",
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
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