package com.example.fetchrewardsexercise.domain.model

import com.example.fetchrewardsexercise.core.util.Constants.Delimiters.WHITESPACE

data class HiringList(
    val items: List<HiringListItem>
)

data class HiringListItem(
    val id: Int,
    val listId: Int,
    val name: String?
) {
    val itemNumber: Int?
        get() = name?.split(WHITESPACE)?.last()?.toIntOrNull()
}
