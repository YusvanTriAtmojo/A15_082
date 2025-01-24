package com.example.uaspam.service

import com.example.uaspam.model.Hewan
import com.example.uaspam.model.HewanDetailResponse
import com.example.uaspam.model.HewanResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HewanService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("hewan")
    suspend fun getHewan(): HewanResponse

    @GET("hewan/{Id_hewan}")
    suspend fun getHewanbyId(@Path("Id_hewan") Id_hewan:String) : HewanDetailResponse

    @POST("hewan/store")
    suspend fun insertHewan(@Body hewan: Hewan)

    @PUT("hewan/{Id_hewan}")
    suspend fun updateHewan(@Path("Id_hewan")Id_hewan:String, @Body hewan: Hewan)

    @DELETE("hewan/{Id_hewan}")
    suspend fun deleteHewan(@Path("Id_hewan") Id_hewan:String): Response<Void>
}