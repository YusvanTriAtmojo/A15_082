package com.example.uaspam.container

import com.example.uaspam.repository.HewanRepository
import com.example.uaspam.repository.KandangRepository
import com.example.uaspam.repository.MonitoringRepository
import com.example.uaspam.repository.NetworkHewanRepository
import com.example.uaspam.repository.NetworkKandangRepository
import com.example.uaspam.repository.NetworkMonitoringRepository
import com.example.uaspam.repository.NetworkPetugasRepository
import com.example.uaspam.repository.PetugasRepository
import com.example.uaspam.service.HewanService
import com.example.uaspam.service.KandangService
import com.example.uaspam.service.MonitoringService
import com.example.uaspam.service.PetugasService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val hewanRepository: HewanRepository
    val petugasRepository: PetugasRepository
}

class HewanContainer: AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/"
    private val json = Json{ ignoreUnknownKeys = true}
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val hewanService: HewanService by lazy {
        retrofit.create(HewanService::class.java)
    }

    override val hewanRepository: HewanRepository by lazy {
        NetworkHewanRepository(hewanService)
    }

    private val petugasService: PetugasService by lazy {
        retrofit.create(PetugasService::class.java)
    }

    override val petugasRepository: PetugasRepository by lazy {
        NetworkPetugasRepository(petugasService)
    }
}