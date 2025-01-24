package com.example.uaspam.repository

import com.example.uaspam.model.Petugas
import com.example.uaspam.model.PetugasDetailResponse
import com.example.uaspam.model.PetugasResponse


interface PetugasRepository {
    suspend fun getPetugas(): PetugasResponse
    suspend fun insertPetugas(petugas: Petugas)
    suspend fun updatePetugas(Id_petugas: String, petugas: Petugas)
    suspend fun deletePetugas(Id_petugas: String)
    suspend fun getPetugasbyId(Id_petugas: String): PetugasDetailResponse
}
