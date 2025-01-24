package com.example.uaspam.ui.view.petugas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.R
import com.example.uaspam.model.Petugas
import com.example.uaspam.model.PetugasDetailResponse
import com.example.uaspam.ui.customwidget.CostumeTopAppBar
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.petugas.DetailPUiState
import com.example.uaspam.ui.viewmodel.petugas.DetailPetugasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPetugasScreen(
    navigateBack: () -> Unit,
    onDeleteClick: (String) -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailPetugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Detail Petugas",
                canNavigateBack = true,
                onRefresh = {
                    viewModel.getPetugasById()
                },
                navigateUp = navigateBack,
                modifier = Modifier
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.keeper1),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxSize(),
            )
            DetailPetugasStatus(
                modifier = Modifier.padding(innerPadding),
                detailPUiState = viewModel.detailPUiState,
                retryAction = { viewModel.getPetugasById() },
                onEditClick = onEditClick,
                onDeleteClick = { id ->
                    viewModel.deletePtg(id)
                    onDeleteClick(id)
                }
            )
        }
    }
}

@Composable
fun DetailPetugasStatus(
    retryAction:() -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (String) -> Unit,
    onEditClick: () -> Unit,
    detailPUiState: DetailPUiState
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    var petugasIdToDelete by rememberSaveable { mutableStateOf("") }
    when(detailPUiState){
        is DetailPUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailPUiState.Success -> {
            ItemDetailPtg(
                petugas = detailPUiState.petugas,
                onDeleteClick = {
                    petugasIdToDelete = detailPUiState.petugas.data3.Id_petugas
                    deleteConfirmationRequired = true
                },
                onEditClick = onEditClick,
                modifier = modifier.fillMaxWidth()
            )
        }
        is DetailPUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(petugasIdToDelete)
            },
            onDeleteCancel = { deleteConfirmationRequired = false },
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Composable
fun ItemDetailPtg(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    petugas: PetugasDetailResponse
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xC0016D47),
            contentColor = Color.White,
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Detail Petugas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF018786),
                    contentColor = Color.White
                ),
            ){
                DetailPetugasContent(petugas = petugas.data3)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (modifier = Modifier.padding(start = 128.dp)) {
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFD20A4E), shape = RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFFFFB300), shape = RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailPetugasContent(
    modifier: Modifier = Modifier,
    petugas: Petugas
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        DetailRow(judul = "ID Petugas", value = petugas.Id_petugas)
        DetailRow(judul = "Nama", value = petugas.Nama_petugas)
        DetailRow(judul = "Jabatan", value = petugas.jabatan)
    }
}

@Composable
fun DetailRow(
    modifier: Modifier = Modifier,
    judul: String,
    value: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$judul ",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}

