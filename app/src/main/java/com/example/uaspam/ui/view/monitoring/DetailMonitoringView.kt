package com.example.uaspam.ui.view.monitoring

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uaspam.model.MonitoringFull

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