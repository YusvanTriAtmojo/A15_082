package com.example.uaspam.repository

import com.example.uaspam.model.Hewan
import com.example.uaspam.model.HewanDetailResponse
import com.example.uaspam.model.HewanResponse

interface HewanRepository {
    suspend fun getHewan(): HewanResponse
    suspend fun insertHewan(hewan: Hewan)
    suspend fun updateHewan(Id_hewan: String, hewan: Hewan)
    suspend fun deleteHewan(Id_hewan: String)
    suspend fun getHewanbyId(Id_hewan: String): HewanDetailResponse
}
