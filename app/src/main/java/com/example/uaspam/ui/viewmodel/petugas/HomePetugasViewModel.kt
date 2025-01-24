package com.example.uaspam.ui.viewmodel.petugas


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.Petugas
import com.example.uaspam.repository.PetugasRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomePUiState {
    data class Success(val petugas: List<Petugas>): HomePUiState()
    object Error : HomePUiState()
    object Loading : HomePUiState()
}

class HomePetugasViewModel(private val ptg: PetugasRepository) : ViewModel() {
    var ptgUiState: HomePUiState by mutableStateOf(HomePUiState.Loading)
        private set

    init {
        getPtg()
    }

    fun getPtg(){
        viewModelScope.launch {
            ptgUiState = HomePUiState.Loading
            ptgUiState = try {
                HomePUiState.Success(ptg.getPetugas().data3)
            } catch (e: IOException){
                HomePUiState.Error
            } catch (e: HttpException){
                HomePUiState.Error
            }
        }
    }
}

