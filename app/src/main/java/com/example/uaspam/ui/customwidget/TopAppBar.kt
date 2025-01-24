package com.example.uaspam.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uaspam.R

@Composable
fun TopAppBar(
    judul: String,
    subjudul: String,
    showImage: Boolean = true,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .background(color = Color(0xFF016D47), shape = RoundedCornerShape(bottomEnd = 80.dp))
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    text = judul,
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = subjudul,
                    fontSize = 15.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                )
            }
            if (showImage) {
                Image(
                    painter = painterResource(id = R.drawable.zoo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(end = 30.dp, top = 10.dp)

                )
            }
        }
    }
}