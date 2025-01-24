package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Hewan (
    val Id_hewan: String,
    val Nama_hewan: String,
    val Tipe_pakan: String,
    val populasi: Int,
    val Zona_wilayah: String,
)
