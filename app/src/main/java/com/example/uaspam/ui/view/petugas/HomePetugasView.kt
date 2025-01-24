package com.example.uaspam.ui.view.petugas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.uaspam.model.Petugas

@Composable
fun PtgLayout(
    petugas: List<Petugas>,
    modifier: Modifier = Modifier,
    onDetailClick: (Petugas) -> Unit,
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(petugas) {ptg ->
            PtgCard(
                petugas = ptg,
                onDetailClick = { onDetailClick(ptg) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun PtgCard(
    petugas: Petugas,
    modifier: Modifier = Modifier,
    onDetailClick: (Petugas) -> Unit,
) {
    Card (modifier = modifier,colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Card (modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),colors = CardDefaults.cardColors(containerColor = Color(0xFF016D47)))
        {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onDetailClick(petugas) },
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color(0xFF018786), shape = RoundedCornerShape(10.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = "Detail",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = petugas.Nama_petugas,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ID: ${petugas.Id_petugas}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = petugas.jabatan,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}