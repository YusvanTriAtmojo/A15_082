package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Petugas(
    val Id_petugas: String,
    val Nama_petugas: String,
    val jabatan: String,
)

