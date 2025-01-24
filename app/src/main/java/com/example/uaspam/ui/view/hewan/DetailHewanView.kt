package com.example.uaspam.ui.view.hewan

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
import com.example.uaspam.model.Hewan

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

