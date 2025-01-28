package com.example.uaspam.ui.view.monitoring

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
import com.example.uaspam.ui.navigation.DestinasiUpdateMonitoring
import com.example.uaspam.ui.viewmodel.PenyediaViewModel
import com.example.uaspam.ui.viewmodel.monitoring.UpdateMonitoringViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMonitoringView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMonitoringViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.updateMtrUIState
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
                title = DestinasiUpdateMonitoring.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ){innerPadding ->
        EntryBody(
            additionalText = "Jika tidak perlu diubah, cukup biarkan jangan di sentuh.",
            insertMUiState = viewModel.updateMtrUIState,
            onValueChange = viewModel::updateMtrState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateMtr()
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