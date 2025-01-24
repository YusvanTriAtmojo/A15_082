package com.example.uaspam.ui.view.petugas

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.R
import com.example.uaspam.model.Petugas
import com.example.uaspam.ui.customwidget.CostumeTopAppBar
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.petugas.HomePUiState
import com.example.uaspam.ui.viewmodel.petugas.HomePetugasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePetugasScreen(
    navigateToItemEntry: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomePetugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection) .background(Color(0xFF01A58B)),
        topBar = {
            CostumeTopAppBar(
                title = "Menu Petugas",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPtg()
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
            Column(modifier = Modifier.padding(innerPadding)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        shape = RoundedCornerShape(50.dp),
                        label = { Text("Search Petugas") },
                        modifier = Modifier
                            .weight(1f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color(0xFFFFFFFF),
                            unfocusedLabelColor = Color(0xFF016D47),
                            focusedLabelColor = Color(0xFFFFFFFF),
                        )
                    )

                    Button(
                        onClick = {
                            viewModel.getPtg()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF018786),
                            contentColor = Color.White
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text("Search")
                    }
                }
                Button(
                    onClick = navigateToItemEntry,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF018786),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(18.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Petugas")
                }

                HomePtgStatus(
                    homePUiState = viewModel.ptgUiState,
                    retryAction = { viewModel.getPtg() },
                    onDetailClick = onDetailClick,
                    searchText = searchText
                )
            }
        }
    }
}


@Composable
fun HomePtgStatus(
    homePUiState: HomePUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    searchText: String,
    onDetailClick: (String) -> Unit
) {
    Card (colors = CardDefaults.cardColors(containerColor = Color(0xFF01A58B))){
        when (homePUiState) {
            is HomePUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
            is HomePUiState.Success -> {
                val filteredPetugas = homePUiState.petugas.filter {
                    it.Nama_petugas.contains(searchText, ignoreCase = true)
                }
                if (filteredPetugas.isEmpty()) {
                    Box (modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Tidak ada data Petugas")
                    }
                } else {
                    PtgLayout(
                        petugas = filteredPetugas,
                        modifier = modifier.fillMaxWidth(),
                        onDetailClick = { onDetailClick(it.Id_petugas) },
                    )
                }
            }
            is HomePUiState.Error -> OnError(
                retryAction,
                modifier = Modifier.fillMaxSize()
            )
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