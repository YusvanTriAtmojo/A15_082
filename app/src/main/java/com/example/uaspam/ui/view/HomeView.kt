package com.example.uaspam.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uaspam.R
import com.example.uaspam.ui.customwidget.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    keHewan: () -> Unit,
    keKandang: () -> Unit,
    kePetugas: () -> Unit,
    keMonitoring: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                judul = "ZOO",
                subjudul =  "Pendataan ZOO",
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.jungle),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Card(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .height(450.dp)
                        .width(500.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xB301A58B)),
                )
                {
                    Text(
                        modifier = Modifier.padding(start = 120.dp, top = 20.dp),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp,
                        color = Color.White,
                        text = "Menu Utama"
                    )
                    Row {
                        Card(
                            onClick = keHewan,
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF016D47)),
                            modifier = Modifier
                                .padding(start = 45.dp, 20.dp)
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.lion),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(70.dp)
                                        .padding(start = 23.dp)

                                )
                                Text(
                                    color = Color.White,
                                    text = "Hewan",
                                    modifier = Modifier.padding(start = 21.dp)
                                )
                            }
                        }
                        Card(
                            onClick = keKandang,
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF016D47)),
                            modifier = Modifier
                                .padding(start = 25.dp, 20.dp, end = 43.dp)
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.cage),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(70.dp)
                                        .padding(start = 20.dp)

                                )
                                Text(
                                    color = Color.White,
                                    text = "Kandang",
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                            }
                        }
                    }
                    Row {
                        Card(
                            onClick = kePetugas,
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF016D47)),
                            modifier = Modifier
                                .padding(start = 45.dp, 20.dp)
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.petugas),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(70.dp)
                                        .padding(start = 20.dp)

                                )
                                Text(
                                    color = Color.White,
                                    text = "Petugas",
                                    modifier = Modifier.padding(start = 14.dp)
                                )
                            }
                        }
                        Card(
                            onClick = keMonitoring,
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF016D47)),
                            modifier = Modifier
                                .padding(start = 25.dp, 20.dp, end = 43.dp)
                                .height(150.dp)
                                .width(150.dp)
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.monitor),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(70.dp)
                                        .padding(start = 20.dp)

                                )
                                Text(
                                    color = Color.White,
                                    text = "Monitoring",
                                    modifier = Modifier.padding(start = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
