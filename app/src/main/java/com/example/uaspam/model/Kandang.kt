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
    val data2: KandangHewan,
)

@Serializable
data class KandangHewan(
    val Id_kandang: String,
    val Id_hewan: String,
    val Nama_hewan: String,
    val kapasitas: Int,
    val lokasi: String
)

@Serializable
data class KandangResponse (
    val status: Boolean,
    val message: String,
    val data2: List<Kandang>
)
