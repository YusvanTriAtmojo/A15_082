package com.example.uaspam.repository

import com.example.uaspam.model.Kandang
import com.example.uaspam.model.KandangDetailResponse
import com.example.uaspam.model.KandangResponse

interface KandangRepository {
    suspend fun getKandang(): KandangResponse
    suspend fun insertKandang(kandang: Kandang)
    suspend fun updateKandang(Id_kandang: String, kandang: Kandang)
    suspend fun deleteKandang(Id_kandang: String)
    suspend fun getKandangbyId(Id_kandang: String): KandangDetailResponse
}
