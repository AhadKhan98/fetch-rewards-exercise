package com.example.fetchrewardsexercise.data.remote.mapper

import com.example.fetchrewardsexercise.data.remote.dto.HiringListDto
import com.example.fetchrewardsexercise.domain.model.HiringList
import com.example.fetchrewardsexercise.domain.model.HiringListItem

fun List<HiringListDto>.mapToDomainModel(): HiringList {
    val mappedListItems = this.map { dtoItem ->
        HiringListItem(
            id = dtoItem.id,
            listId = dtoItem.listId,
            name = dtoItem.name
        )
    }
    return HiringList(mappedListItems)
}