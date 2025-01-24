package com.example.uaspam.ui.view.petugas

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
import com.example.uaspam.model.Petugas

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
