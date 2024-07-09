package com.example.fetchrewardsexercise.presentation.screen.hiring_list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.fetchrewardsexercise.R

@Composable
fun HiringListLoading(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = stringResource(id = R.string.hiring_list_loading))
    }
}