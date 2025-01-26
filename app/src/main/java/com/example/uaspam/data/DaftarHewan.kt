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
    ): List<String> {
        // Mengakses state yang ada di ViewModel
        val daftarhewan = daftarHwn.hwnUiState

        // Mengambil daftar hewan jika state adalah Success
        return if (daftarhewan is HomeHUiState.Success) {
            daftarhewan.hewan.map { it.Id_hewan }
        } else {
            emptyList()
        }
    }
}
