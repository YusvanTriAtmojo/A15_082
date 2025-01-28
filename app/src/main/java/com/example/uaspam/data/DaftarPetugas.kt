package com.example.uaspam.data

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.petugas.HomePUiState
import com.example.uaspam.ui.viewmodel.petugas.HomePetugasViewModel

object DaftarPetugas {
    @Composable
    fun ListPetugas(
        daftarPtgs: HomePetugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<Pair<String, String>> {
        val daftarpetugas = daftarPtgs.ptgUiState

        return if (daftarpetugas is HomePUiState.Success) {
            daftarpetugas.petugas
                .filter { it.jabatan == "Dokter Hewan" }
                .map { Pair(it.Id_petugas, it.Nama_petugas) }
        } else {
            emptyList()
        }
    }
}


