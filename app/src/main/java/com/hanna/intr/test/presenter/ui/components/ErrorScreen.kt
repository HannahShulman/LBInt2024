package com.hanna.intr.test.presenter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorScreen(errorMsg: String, retryAction: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),

        ) {
        Text(text = errorMsg, textAlign = TextAlign.Center)
        Button(onClick = { retryAction() }) {
            Text(text = "Retry", textAlign = TextAlign.Center)
        }
    }
}