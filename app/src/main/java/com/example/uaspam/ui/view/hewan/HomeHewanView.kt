package com.example.uaspam.ui.view.hewan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Button
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
import com.example.uaspam.model.Hewan
import com.example.uaspam.ui.viewmodel.hewan.HomeHUiState

@Composable
fun HomeHwnStatus(
    homeHUiState: HomeHUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    searchText: String,
    onDetailClick: (String) -> Unit
) {
    Card ( colors = CardDefaults.cardColors(containerColor = Color(0xB301A58B))){
        when (homeHUiState) {
            is HomeHUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
            is HomeHUiState.Success -> {
                val filteredHewan = homeHUiState.hewan.filter {
                    it.Nama_hewan.contains(searchText, ignoreCase = true)
                }
                if (filteredHewan.isEmpty()) {
                    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Tidak ada data Hewan")
                    }
                } else {
                    HwnLayout(
                        hewan = filteredHewan,
                        modifier = modifier.fillMaxWidth(),
                        onDetailClick = { onDetailClick(it.Id_hewan) },
                    )
                }
            }
            is HomeHUiState.Error -> OnError(retryAction, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    Text("Loading..")
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Loading Failed")
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun HwnLayout(
    hewan: List<Hewan>,
    modifier: Modifier = Modifier,
    onDetailClick: (Hewan) -> Unit ,
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(15.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(hewan) {hwn ->
            HwnCard(
                hewan = hwn,
                onDetailClick = { onDetailClick(hwn) },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}


@Composable
fun HwnCard(
    hewan: Hewan,
    modifier: Modifier = Modifier,
    onDetailClick: (Hewan) -> Unit,
    backgroundColor: Color? = null
) {
    Card (modifier = modifier,colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor ?: if (hewan.Tipe_pakan == "Karnivora") {
                    Color(0xFFD20A4E)
                } else if (hewan.Tipe_pakan == "Herbivora") {
                    Color(0xFF5CAD5F)
                } else {
                    Color(0xFF0B83E3)
                }
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onDetailClick(hewan) },
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
                        text = hewan.Nama_hewan,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "ID: ${hewan.Id_hewan}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Zona ${hewan.Zona_wilayah}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = hewan.Tipe_pakan,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}