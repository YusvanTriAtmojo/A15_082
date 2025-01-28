package com.example.uaspam.ui.view.monitoring

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import com.example.uaspam.model.MonitoringDetailResponse
import com.example.uaspam.model.MonitoringFull
import com.example.uaspam.ui.viewmodel.monitoring.DetailMUiState

@Composable
fun DetailMonitoringStatus(
    retryAction:() -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (String) -> Unit,
    onEditClick: () -> Unit,
    detailMUiState: DetailMUiState
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    var monitoringIdToDelete by rememberSaveable { mutableStateOf("") }
    when(detailMUiState){
        is DetailMUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())

        is DetailMUiState.Success -> {
            ItemDetailMtr(
                monitoring = detailMUiState.monitoring,
                onDeleteClick = {
                    monitoringIdToDelete = detailMUiState.monitoring.data4.Id_monitoring
                    deleteConfirmationRequired = true
                },
                onEditClick = onEditClick,
                modifier = modifier.fillMaxWidth()
            )
        }
        is DetailMUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick(monitoringIdToDelete)
            },
            onDeleteCancel = { deleteConfirmationRequired = false },
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ItemDetailMtr(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    monitoring: MonitoringDetailResponse,
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
                text = "Detail Monitoring",
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
                DetailMonitoringContent(monitoring = monitoring.data4)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (modifier = Modifier.padding(start = 128.dp)) {
                Button(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .size(50.dp)
                        .background(color = Color(0xFFD20A4E), shape = RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
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
fun DetailMonitoringContent(
    modifier: Modifier = Modifier,
    monitoring: MonitoringFull,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        DetailRow(judul = "ID Monitoring", value = monitoring.Id_monitoring)
        DetailRow(judul = "ID Petugas", value = monitoring.Id_petugas)
        DetailRow(judul = "Petugas", value = monitoring.Nama_petugas)
        DetailRow(judul = "Nama Hewan", value = monitoring.Nama_hewan)
        DetailRow(judul = "ID Kandang", value = monitoring.Id_kandang)
        DetailRow(judul = "Tanggal", value = monitoring.Tanggal_monitoring)
        DetailRow(judul = "Hewan Sakit", value = monitoring.Hewan_sakit.toString())
        DetailRow(judul = "Hewan Sehat", value = monitoring.Hewan_sehat.toString())
        DetailRow(judul = "Status", value = monitoring.Status)
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