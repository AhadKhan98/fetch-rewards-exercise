package com.example.fetchrewardsexercise.presentation.screen.hiring_list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fetchrewardsexercise.R
import com.example.fetchrewardsexercise.domain.model.GroupedHiringList
import com.example.fetchrewardsexercise.domain.model.HiringListItem

@Composable
fun LazyHiringList(groupedHiringList: List<GroupedHiringList>) {
    val listIdSet = remember { mutableSetOf<Int>() }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groupedHiringList.map { groupedHiringList ->
            if (groupedHiringList.listId !in listIdSet) {
                item { ListIdHeader(id = groupedHiringList.listId) }
                listIdSet.add(groupedHiringList.listId)
            }
            items(groupedHiringList.items) { hiringListItem ->
                HiringListCell(item = hiringListItem)
            }
        }
    }
}

@Composable
private fun ListIdHeader(id: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.hiring_list_row_header, id.toString()),
            style = MaterialTheme.typography.headlineLarge
        )
        Divider(thickness = 2.dp)
    }
}

@Composable
private fun HiringListCell(item: HiringListItem) {
    Text(text = item.name.orEmpty())
}

