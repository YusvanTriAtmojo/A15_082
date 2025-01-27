package com.example.uaspam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.HewanApplications
import com.example.uaspam.ui.viewmodel.hewan.DetailHewanViewModel
import com.example.uaspam.ui.viewmodel.hewan.HomeHewanViewModel
import com.example.uaspam.ui.viewmodel.hewan.InsertHewanViewModel
import com.example.uaspam.ui.viewmodel.hewan.UpdateHewanViewModel
import com.example.uaspam.ui.viewmodel.kandang.DetailKandangViewModel
import com.example.uaspam.ui.viewmodel.kandang.HomeKandangViewModel
import com.example.uaspam.ui.viewmodel.kandang.InsertKandangViewModel
import com.example.uaspam.ui.viewmodel.kandang.UpdateKandangViewModel
import com.example.uaspam.ui.viewmodel.monitoring.DetailMonitoringViewModel
import com.example.uaspam.ui.viewmodel.monitoring.HomeMonitoringViewModel
import com.example.uaspam.ui.viewmodel.monitoring.InsertMonitoringViewModel
import com.example.uaspam.ui.viewmodel.monitoring.UpdateMonitoringViewModel
import com.example.uaspam.ui.viewmodel.petugas.DetailPetugasViewModel
import com.example.uaspam.ui.viewmodel.petugas.HomePetugasViewModel
import com.example.uaspam.ui.viewmodel.petugas.InsertPetugasViewModel
import com.example.uaspam.ui.viewmodel.petugas.UpdatePetugasViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeHewanViewModel(
                aplikasiHwn().container.hewanRepository
            )
        }
        initializer {
            InsertHewanViewModel(
                aplikasiHwn().container.hewanRepository
            )
        }
        initializer {
            DetailHewanViewModel(
                createSavedStateHandle(),
                aplikasiHwn().container.hewanRepository
            )
        }
        initializer {
            UpdateHewanViewModel(
                aplikasiHwn().container.hewanRepository,
                createSavedStateHandle(),
            )
        }
        initializer {
            HomePetugasViewModel(
                aplikasiHwn().container.petugasRepository
            )
        }
        initializer {
            InsertPetugasViewModel(
                aplikasiHwn().container.petugasRepository
            )
        }
        initializer {
            DetailPetugasViewModel(
                createSavedStateHandle(),
                aplikasiHwn().container.petugasRepository
            )
        }
        initializer {
            UpdatePetugasViewModel(
                aplikasiHwn().container.petugasRepository,
                createSavedStateHandle(),
            )
        }
        initializer {
            HomeKandangViewModel(
                aplikasiHwn().container.kandangRepository
            )
        }
        initializer {
            InsertKandangViewModel(
                aplikasiHwn().container.kandangRepository
            )
        }
        initializer {
            DetailKandangViewModel(
                createSavedStateHandle(),
                aplikasiHwn().container.kandangRepository
            )
        }
        initializer {
            UpdateKandangViewModel(
                aplikasiHwn().container.kandangRepository,
                createSavedStateHandle(),
            )
        }
        initializer {
            HomeMonitoringViewModel(
                aplikasiHwn().container.monitoringRepository
            )
        }
        initializer {
            InsertMonitoringViewModel(
                aplikasiHwn().container.monitoringRepository
            )
        }
        initializer {
            DetailMonitoringViewModel(
                createSavedStateHandle(),
                aplikasiHwn().container.monitoringRepository
            )
        }
        initializer {
            UpdateMonitoringViewModel(
                aplikasiHwn().container.monitoringRepository,
                createSavedStateHandle(),
            )
        }
    }
}

fun CreationExtras.aplikasiHwn(): HewanApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HewanApplications)