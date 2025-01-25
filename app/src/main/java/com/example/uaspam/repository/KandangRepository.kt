package com.example.uaspam.repository

import com.example.uaspam.model.Kandang
import com.example.uaspam.model.KandangDetailResponse
import com.example.uaspam.model.KandangResponse
import com.example.uaspam.service.KandangService
import java.io.IOException

interface KandangRepository {
    suspend fun getKandang(): KandangResponse
    suspend fun insertKandang(kandang: Kandang)
    suspend fun updateKandang(Id_kandang: String, kandang: Kandang)
    suspend fun deleteKandang(Id_kandang: String)
    suspend fun getKandangbyId(Id_kandang: String): KandangDetailResponse
}

class NetworkKandangRepository(
    private val kandangApiService: KandangService
) : KandangRepository {
    override suspend fun insertKandang(kandang: Kandang) {
        kandangApiService.insertKandang(kandang)
    }

    override suspend fun updateKandang(Id_kandang: String, kandang: Kandang) {
        kandangApiService.updateKandang(Id_kandang, kandang)
    }

    override suspend fun deleteKandang(Id_kandang: String) {
        try{
            val response = kandangApiService.deleteKandang(Id_kandang)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete kandang. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getKandang(): KandangResponse {
        return kandangApiService.getKandang()
    }
    override suspend fun getKandangbyId(Id_kandang: String): KandangDetailResponse {
        return kandangApiService.getKandangbyId(Id_kandang)
    }
}


