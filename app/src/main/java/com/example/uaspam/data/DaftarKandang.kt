package com.example.uaspam.data

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.hewan.HomeHUiState
import com.example.uaspam.ui.viewmodel.hewan.HomeHewanViewModel
import com.example.uaspam.ui.viewmodel.kandang.HomeKUiState
import com.example.uaspam.ui.viewmodel.kandang.HomeKandangViewModel

object DaftarKandang {
    @Composable
    fun ListKandang(
        daftarKdg: HomeKandangViewModel = viewModel(factory = PenyediaViewModel.Factory),
        daftarHwn: HomeHewanViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<Pair<String, String>> {
        val daftarkandang = daftarKdg.kdgUiState
        val daftarhewan = daftarHwn.hwnUiState

        return if (daftarkandang is HomeKUiState.Success && daftarhewan is HomeHUiState.Success) {
            daftarkandang.kandang.map { kandang ->
                val namaHewan = daftarhewan.hewan.find { it.Id_hewan == kandang.Id_hewan }?.Nama_hewan ?: "Unknown"
                Pair(kandang.Id_kandang, "${kandang.Id_kandang} - $namaHewan")
            }
        } else {
            emptyList()
        }
    }
}


