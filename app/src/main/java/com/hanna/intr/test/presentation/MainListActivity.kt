package com.hanna.intr.test.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.hanna.intr.test.domain.usecases.FetchAllLaunchesUseCase
import com.hanna.intr.test.presentation.ui.components.LaunchesNavGraph
import com.hanna.intr.test.ui.theme.LBInt2024Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainListActivity : ComponentActivity() {

    @Inject
    lateinit var useCase: FetchAllLaunchesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            LBInt2024Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LaunchesNavGraph()
                }
            }
        }
    }
}