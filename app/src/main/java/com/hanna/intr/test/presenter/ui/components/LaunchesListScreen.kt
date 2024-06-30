package com.hanna.intr.test.presenter.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hanna.intr.test.domain.models.Launch
import com.hanna.intr.test.presenter.routes.NavRoutes
import com.hanna.intr.test.presenter.viewmodels.LaunchesListViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LaunchesListScreen(navHostController: NavHostController, launchesListViewModel: LaunchesListViewModel = getViewModel() ){

    val launchesListScreenUiState = launchesListViewModel.launchesListUiState.collectAsState()
    val listState = rememberLazyListState()

    if (launchesListScreenUiState.value.isLoading) {
        LoadingScreen()
    } else if (launchesListScreenUiState.value.launchesList.orEmpty().isNotEmpty()) {
        LazyColumn(state = listState) {
            items(launchesListScreenUiState.value.launchesList?.size ?: 0) { item ->
                launchesListScreenUiState.value.launchesList?.get(item)?.let {
                    LaunchItem(it) { id ->
                        navHostController.navigate(NavRoutes.LaunchDetail.createRouteWithArgs(id)) }
                }
            }
        }
    } else if (!launchesListScreenUiState.value.error.isNullOrEmpty()) {
        launchesListScreenUiState.value.error?.let {
            ErrorScreen(errorMsg = it, retryAction = { launchesListViewModel.retryFetchAllLaunches() })
        }
    }
}

@Composable
fun LaunchItem(launch: Launch, onItemClicked: (id: String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClicked(launch.id) }
            .clip(RoundedCornerShape(20))
            .border(border = BorderStroke(1.dp, Color.Red), shape = RoundedCornerShape(6.dp))
            .background(Brush.linearGradient(colors = listOf(Color.Yellow, Color.LightGray, Color.Cyan)))
            .padding(8.dp),
    ) {
        Text(
            text = "Launch date: ${launch.date}",
        )
        val image = Icons.Default.Check.takeIf { launch.success } ?: Icons.Default.Clear
        val contentDescription = "Successful Launch Image".takeIf { launch.success } ?: "Failed Launch Image"
        Icon(imageVector = image, contentDescription = contentDescription)
    }
}

@Preview(showBackground = true)
@Composable
fun LaunchItemPreview() {
    LaunchItem(
        Launch("", "name", "date", true, details = "abcdefg")
    ) { _ -> }
}