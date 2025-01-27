package com.example.uaspam.repository

import com.example.uaspam.model.Monitoring
import com.example.uaspam.model.MonitoringDetailResponse
import com.example.uaspam.model.MonitoringResponse
import com.example.uaspam.service.MonitoringService
import java.io.IOException

interface MonitoringRepository {
    suspend fun getMonitoring(): MonitoringResponse
    suspend fun insertMonitoring(monitoring: Monitoring)
    suspend fun updateMonitoring(Id_monitoring: String, monitoring: Monitoring)
    suspend fun deleteMonitoring(Id_monitoring: String)
    suspend fun getMonitoringbyId(Id_monitoring: String): MonitoringDetailResponse

}

class NetworkMonitoringRepository(
    private val monitoringApiService: MonitoringService
) : MonitoringRepository {
    override suspend fun insertMonitoring(monitoring: Monitoring) {
        monitoringApiService.insertMonitoring(monitoring)
    }

    override suspend fun updateMonitoring(Id_monitoring: String, monitoring: Monitoring) {
        monitoringApiService.updateMonitoring(Id_monitoring, monitoring)
    }

    override suspend fun deleteMonitoring(Id_monitoring: String) {
        try{
            val response = monitoringApiService.deleteMonitoring(Id_monitoring)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete monitoring. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun getMonitoring(): MonitoringResponse {
        return monitoringApiService.getMonitoring()
    }
    override suspend fun getMonitoringbyId(Id_monitoring: String): MonitoringDetailResponse {
        return monitoringApiService.getMonitoringbyId(Id_monitoring)
    }
}
