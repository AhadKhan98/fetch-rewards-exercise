package com.example.fetchrewardsexercise.presentation.screen.hiring_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fetchrewardsexercise.R

@Composable
fun HiringListErrorMessage(
    modifier: Modifier = Modifier,
    message: String?,
    onRetryFetch: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val errorMessage = message ?: stringResource(id = R.string.hiring_list_unknown_error)
        Text(text = errorMessage, textAlign = TextAlign.Center)
        Button(onClick = onRetryFetch) {
            Text(text = stringResource(id = R.string.hiring_list_retry))
        }
    }
}