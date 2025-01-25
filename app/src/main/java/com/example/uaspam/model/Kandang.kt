package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Kandang(
    val Id_kandang: String,
    val Id_hewan: String,
    val kapasitas: Int,
    val lokasi: String,
)

@Serializable
data class KandangDetailResponse (
    val status: Boolean,
    val message: String,
    val data2: Kandang,
)
