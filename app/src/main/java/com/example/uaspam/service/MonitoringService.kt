package com.example.uaspam.service

import com.example.uaspam.model.Monitoring
import com.example.uaspam.model.MonitoringDetailResponse
import com.example.uaspam.model.MonitoringResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MonitoringService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("monitoring")
    suspend fun getMonitoring(): MonitoringResponse

    @GET("monitoring/{Id_monitoring}")
    suspend fun getMonitoringbyId(@Path("Id_monitoring") Id_monitoring:String) : MonitoringDetailResponse

    @POST("monitoring/store")
    suspend fun insertMonitoring(@Body monitoring: Monitoring)

    @PUT("monitoring/{Id_monitoring}")
    suspend fun updateMonitoring(@Path("Id_monitoring")Id_monitoring:String, @Body monitoring: Monitoring)

    @DELETE("monitoring/{Id_monitoring}")
    suspend fun deleteMonitoring(@Path("Id_monitoring") Id_monitoring:String): Response<Void>

}