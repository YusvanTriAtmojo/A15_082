package com.example.uaspam.service

import com.example.uaspam.model.Petugas
import com.example.uaspam.model.PetugasDetailResponse
import com.example.uaspam.model.PetugasResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PetugasService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("petugas")
    suspend fun getPetugas(): PetugasResponse

    @GET("petugas/{Id_petugas}")
    suspend fun getPetugasbyId(@Path("Id_petugas") Id_petugas:String) : PetugasDetailResponse

    @POST("petugas/store")
    suspend fun insertPetugas(@Body petugas: Petugas)

    @PUT("petugas/{Id_petugas}")
    suspend fun updatePetugas(@Path("Id_petugas")Id_petugas: String, @Body petugas: Petugas)

    @DELETE("petugas/{Id_petugas}")
    suspend fun deletePetugas(@Path("Id_petugas") Id_petugas:String): Response<Void>
}