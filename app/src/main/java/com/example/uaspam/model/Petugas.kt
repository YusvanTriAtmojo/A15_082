package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Petugas(
    val Id_petugas: String,
    val Nama_petugas: String,
    val jabatan: String,
)

@Serializable
data class PetugasDetailResponse (
    val status: Boolean,
    val message: String,
    val data3: Petugas
)

@Serializable
data class PetugasResponse (
    val status: Boolean,
    val message: String,
    val data3: List<Petugas>
)

