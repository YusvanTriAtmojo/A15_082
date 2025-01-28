package com.example.uaspam.data

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.hewan.HomeHUiState
import com.example.uaspam.ui.viewmodel.hewan.HomeHewanViewModel

object DaftarHewan {
    @Composable
    fun ListHewan(
        daftarHwn: HomeHewanViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<Pair<String, String>> {
        val daftarhewan = daftarHwn.hwnUiState
        return if (daftarhewan is HomeHUiState.Success) {
            daftarhewan.hewan.map { Pair(it.Id_hewan, it.Nama_hewan) }
        } else {
            emptyList()
        }
    }
}
