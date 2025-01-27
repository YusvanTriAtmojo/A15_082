package com.example.uaspam.model

import kotlinx.serialization.Serializable

@Serializable
data class Monitoring (
    val Id_monitoring: String,
    val Id_petugas: String,
    val Id_kandang: String,
    val Tanggal_monitoring: String,
    val Hewan_sakit: Int,
    val Hewan_sehat: Int,
    val Status: String,
)

@Serializable
data class MonitoringFull(
    val Id_monitoring: String,
    val Id_petugas: String,
    val Nama_petugas: String,
    val Nama_hewan: String,
    val Id_kandang: String,
    val Tanggal_monitoring: String,
    val Hewan_sakit: Int,
    val Hewan_sehat: Int,
    val Status: String,
)

@Serializable
data class MonitoringDetailResponse (
    val status: Boolean,
    val message: String,
    val data4: MonitoringFull
)

@Serializable
data class MonitoringResponse (
    val status: Boolean,
    val message: String,
    val data4: List<Monitoring>
)


