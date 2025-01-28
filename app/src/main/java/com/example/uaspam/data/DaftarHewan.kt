package com.example.uaspam.data

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.hewan.HomeHUiState
import com.example.uaspam.ui.viewmodel.hewan.HomeHewanViewModel
import com.example.uaspam.ui.viewmodel.kandang.HomeKUiState
import com.example.uaspam.ui.viewmodel.kandang.HomeKandangViewModel

object DaftarHewan {
    @Composable
    fun ListHewan(
        daftarHwn: HomeHewanViewModel = viewModel(factory = PenyediaViewModel.Factory),
        kandangVm: HomeKandangViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<Pair<String, String>> {
        val daftarhewan = daftarHwn.hwnUiState
        val kandangState = kandangVm.kdgUiState

        // mengambil id hewan yang sudah terpakai
        val idHewanTerpakai = if (kandangState is HomeKUiState.Success) {
            kandangState.kandang.map { it.Id_hewan }
        } else {
            emptyList()
        }
        return if (daftarhewan is HomeHUiState.Success) {
            // Filter berdasarkan yang belum terpakai
            daftarhewan.hewan
                .filter { it.Id_hewan !in idHewanTerpakai }
                .map { Pair(it.Id_hewan, it.Nama_hewan) }
        } else {
            emptyList()
        }
    }
}

