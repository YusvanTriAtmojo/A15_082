package com.example.uaspam.ui.viewmodel.hewan


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.model.HewanDetailResponse
import com.example.uaspam.repository.HewanRepository
import com.example.uaspam.ui.navigation.DestinasiDetailHewan
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class DetailHUiState {
    data class Success(val hewan: HewanDetailResponse): DetailHUiState()
    object Error : DetailHUiState()
    object Loading : DetailHUiState()
}

class DetailHewanViewModel(
    savedStateHandle: SavedStateHandle,
    private val hwn: HewanRepository
) : ViewModel() {
    var detailHUiState: DetailHUiState by mutableStateOf(DetailHUiState.Loading)
        private set

    private val id: String = checkNotNull(savedStateHandle[DestinasiDetailHewan.IDH])

    init {
        getHewanById()
    }
    fun getHewanById(){
        viewModelScope.launch {
            detailHUiState = DetailHUiState.Loading
            detailHUiState = try {
                val hewan = hwn.getHewanbyId(id)
                DetailHUiState.Success(hewan)
            } catch (e: IOException){
                DetailHUiState.Error
            } catch (e: HttpException) {
                DetailHUiState.Error
            }
        }
    }
    fun deleteHwn(id: String){
        viewModelScope.launch {
            try {
                hwn.deleteHewan(id)
            } catch (e: IOException){
                DetailHUiState.Error
            } catch (e: HttpException) {
                DetailHUiState.Error
            }
        }
    }
}
