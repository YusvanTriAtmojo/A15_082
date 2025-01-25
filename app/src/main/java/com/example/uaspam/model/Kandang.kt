package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Kandang(
    val Id_kandang: String,
    val Id_hewan: String,
    val kapasitas: Int,
    val lokasi: String,
)