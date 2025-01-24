package com.example.uaspam.ui.view.hewan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uaspam.model.Hewan
import com.example.uaspam.model.HewanDetailResponse
import com.example.uaspam.ui.viewmodel.hewan.DetailHUiState

@Composable
fun DetailHewanStatus(
    retryAction:() -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (String) -> Unit,
    detailHUiState: DetailHUiState
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    var hewanIdToDelete by rememberSaveable { mutableStateOf("") }
    when(detailHUiState){
        is DetailHUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailHUiState.Success -> {
            ItemDetailHwn(
                hewan = detailHUiState.hewan,
                onDeleteClick = {
                    hewanIdToDelete = detailHUiState.hewan.data1.Id_hewan
                    deleteConfirmationRequired = true
                },

                modifier = modifier.fillMaxWidth()
            )
        }
        is DetailHUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(hewanIdToDelete)
            },
            onDeleteCancel = { deleteConfirmationRequired = false },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ItemDetailHwn(
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
    hewan: HewanDetailResponse
) {
    Card (
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
                text = "Detail Hewan",
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
                DetailHewanContent(hewan = hewan.data1)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button (
                onClick = onDeleteClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD20A4E),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }
        }
    }
}


@Composable
fun DetailHewanContent(
    modifier: Modifier = Modifier,
    hewan: Hewan
) {
    Column (
        modifier = modifier.fillMaxWidth().padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        DetailRow(judul = "ID Hewan", value = hewan.Id_hewan)
        DetailRow(judul = "Nama Hewan", value = hewan.Nama_hewan)
        DetailRow(judul = "Tipe Pakan", value = hewan.Tipe_pakan)
        DetailRow(judul = "Populasi", value = hewan.populasi.toString())
        DetailRow(judul = "Zona Wilayah", value = hewan.Zona_wilayah)
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
    AlertDialog (onDismissRequest = { /* Do nothing */ },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton (onClick = onDeleteCancel) {
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


