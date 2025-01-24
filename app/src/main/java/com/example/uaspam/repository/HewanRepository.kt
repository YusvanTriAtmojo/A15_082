package com.example.uaspam.repository

import com.example.uaspam.model.Hewan
import com.example.uaspam.model.HewanDetailResponse
import com.example.uaspam.model.HewanResponse
import com.example.uaspam.service.HewanService
import java.io.IOException

interface HewanRepository {
    suspend fun getHewan(): HewanResponse
    suspend fun insertHewan(hewan: Hewan)
    suspend fun updateHewan(Id_hewan: String, hewan: Hewan)
    suspend fun deleteHewan(Id_hewan: String)
    suspend fun getHewanbyId(Id_hewan: String): HewanDetailResponse
}

class NetworkHewanRepository(
    private val hewanApiService: HewanService
) : HewanRepository {
    override suspend fun insertHewan(hewan: Hewan) {
        hewanApiService.insertHewan(hewan)
    }

    override suspend fun updateHewan(Id_hewan: String, hewan: Hewan) {
        hewanApiService.updateHewan(Id_hewan, hewan)
    }

    override suspend fun deleteHewan(Id_hewan: String) {
        try{
            val response = hewanApiService.deleteHewan(Id_hewan)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete hewan. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getHewan(): HewanResponse{
        return hewanApiService.getHewan()
    }
    override suspend fun getHewanbyId(Id_hewan: String): HewanDetailResponse{
        return hewanApiService.getHewanbyId(Id_hewan)
    }
}

