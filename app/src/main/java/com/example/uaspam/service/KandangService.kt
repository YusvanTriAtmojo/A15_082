package com.example.uaspam.service

import com.example.uaspam.model.Kandang
import com.example.uaspam.model.KandangDetailResponse
import com.example.uaspam.model.KandangResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KandangService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("kandang")
    suspend fun getKandang(): KandangResponse

    @GET("kandang/{Id_kandang}")
    suspend fun getKandangbyId(@Path("Id_kandang") Id_kandang:String) : KandangDetailResponse

    @POST("kandang/store")
    suspend fun insertKandang(@Body kandang: Kandang)

    @PUT("kandang/{Id_kandang}")
    suspend fun updateKandang(@Path("Id_kandang")Id_kandang:String, @Body kandang: Kandang)

    @DELETE("kandang/{Id_kandang}")
    suspend fun deleteKandang(@Path("Id_kandang") Id_kandang:String): Response<Void>
}