package com.example.uaspam.ui.view.kandang

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
import com.example.uaspam.model.KandangDetailResponse
import com.example.uaspam.model.KandangHewan
import com.example.uaspam.ui.customwidget.CostumeTopAppBar
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.kandang.DetailKUiState
import com.example.uaspam.ui.viewmodel.kandang.DetailKandangViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailKandangScreen(
    navigateBack: () -> Unit,
    onDeleteClick: (String) -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailKandangViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CostumeTopAppBar(
                title = "Detail Kandang",
                canNavigateBack = true,
                onRefresh = {
                    viewModel.getKandangById()
                },
                navigateUp = navigateBack,
                modifier = Modifier
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.kandang),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxSize(),
            )
            DetailKandangStatus(
                modifier = Modifier.padding(innerPadding),
                detailKUiState = viewModel.detailKUiState,
                retryAction = { viewModel.getKandangById() },
                onEditClick = onEditClick,
                onDeleteClick = { id ->
                    viewModel.deleteKdg(id)
                    onDeleteClick(id)
                }
            )
        }
    }
}

@Composable
fun DetailKandangStatus(
    retryAction:() -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (String) -> Unit,
    onEditClick: () -> Unit,
    detailKUiState: DetailKUiState
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    var kandangIdToDelete by rememberSaveable { mutableStateOf("") }
    when(detailKUiState){
        is DetailKUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailKUiState.Success -> {
            ItemDetailKdg(
                kandang = detailKUiState.kandang,
                onDeleteClick = {
                    kandangIdToDelete = detailKUiState.kandang.data2.Id_kandang
                    deleteConfirmationRequired = true
                },
                onEditClick = onEditClick,
                modifier = modifier.fillMaxWidth()
            )
        }
        is DetailKUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(kandangIdToDelete)
            },
            onDeleteCancel = { deleteConfirmationRequired = false },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ItemDetailKdg(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    kandang: KandangDetailResponse,
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
                text = "Detail Kandang",
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
                DetailKandangContent(kandang = kandang.data2)
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
                        contentDescription = "Edit",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailKandangContent(
    modifier: Modifier = Modifier,
    kandang: KandangHewan,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        DetailRow(judul = "ID Kandang", value = kandang.Id_kandang)
        DetailRow(judul = "ID Hewan", value = kandang.Id_hewan)
        DetailRow(judul = "Nama Hewan", value = kandang.Nama_hewan)
        DetailRow(judul = "Kapasitas", value = kandang.kapasitas.toString())
        DetailRow(judul = "Lokasi", value = kandang.lokasi)
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
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { },
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
