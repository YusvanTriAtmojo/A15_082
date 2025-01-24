package com.example.uaspam.repository

import com.example.uaspam.model.Petugas
import com.example.uaspam.model.PetugasDetailResponse
import com.example.uaspam.model.PetugasResponse
import com.example.uaspam.service.PetugasService
import java.io.IOException


interface PetugasRepository {
    suspend fun getPetugas(): PetugasResponse
    suspend fun insertPetugas(petugas: Petugas)
    suspend fun updatePetugas(Id_petugas: String, petugas: Petugas)
    suspend fun deletePetugas(Id_petugas: String)
    suspend fun getPetugasbyId(Id_petugas: String): PetugasDetailResponse
}

class NetworkPetugasRepository(
    private val petugasApiService: PetugasService
) : PetugasRepository {
    override suspend fun insertPetugas(petugas: Petugas) {
        petugasApiService.insertPetugas(petugas)
    }

    override suspend fun updatePetugas(Id_petugas: String, petugas: Petugas) {
        petugasApiService.updatePetugas(Id_petugas, petugas)
    }

    override suspend fun deletePetugas(Id_petugas: String) {
        try{
            val response = petugasApiService.deletePetugas(Id_petugas)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete petugas. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getPetugas(): PetugasResponse {
        return petugasApiService.getPetugas()
    }
    override suspend fun getPetugasbyId(Id_petugas: String): PetugasDetailResponse {
        return petugasApiService.getPetugasbyId(Id_petugas)
    }
}
