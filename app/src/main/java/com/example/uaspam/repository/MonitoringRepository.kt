package com.example.uaspam.repository

import com.example.uaspam.model.Monitoring
import com.example.uaspam.model.MonitoringDetailResponse
import com.example.uaspam.model.MonitoringResponse

interface MonitoringRepository {
    suspend fun getMonitoring(): MonitoringResponse
    suspend fun insertMonitoring(monitoring: Monitoring)
    suspend fun updateMonitoring(Id_monitoring: String, monitoring: Monitoring)
    suspend fun deleteMonitoring(Id_monitoring: String)
    suspend fun getMonitoringbyId(Id_monitoring: String): MonitoringDetailResponse

}
