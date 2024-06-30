package com.hanna.intr.test.presenter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hanna.intr.test.R
import com.hanna.intr.test.presenter.viewmodels.LaunchByIdViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LaunchDetailScreen(launchId: String?, launchByIdViewModel: LaunchByIdViewModel = getViewModel()){
    val launchesListScreenUiState = launchByIdViewModel.launchByIdUiState.collectAsState()
    LaunchedEffect(key1 = "") {
        launchByIdViewModel.fetchLaunchById(launchId)
    }

    Column {

        if (launchesListScreenUiState.value.isLoading) {
            LoadingScreen()
        } else if (launchesListScreenUiState.value.launch != null) {
            launchesListScreenUiState.value.launch?.let { launch ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.Green.takeIf { launch.success } ?: Color.Red
                        )
                        .padding(8.dp)
                ) {
                    Text(text = launch.date, Modifier.padding(4.dp))
                    Text(text = launch.name, Modifier.padding(4.dp))
                    Text(text = launch.details, Modifier.padding(4.dp))
                    Text(
                        text = stringResource(R.string.launch_was_successful.takeIf { launch.success } ?: R.string.launch_failed),
                        Modifier.padding(4.dp)
                    )
                }
            }
        } else if (launchesListScreenUiState.value.error.orEmpty().isNotEmpty()) {
            ErrorScreen(errorMsg = launchesListScreenUiState.value.error.orEmpty()) {
            }
        }
    }
}