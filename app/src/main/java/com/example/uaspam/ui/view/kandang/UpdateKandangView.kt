package com.example.uaspam.ui.view.kandang

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.customwidget.CostumeTopAppBar
import com.example.uaspam.ui.navigation.DestinasiUpdateKandang
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.kandang.UpdateKandangViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKandangView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateKandangViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.updateKdgUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateKandang.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){innerPadding ->
        EntryBody(
            additionalText = "Jika tidak perlu diubah, cukup biarkan jangan di sentuh.",
            insertKUiState = viewModel.updateKdgUIState,
            onKandangValueChange = viewModel::updateState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKandang()
                    navigateBack()
                }
            },
            onCancelClick = {
                navigateBack()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}