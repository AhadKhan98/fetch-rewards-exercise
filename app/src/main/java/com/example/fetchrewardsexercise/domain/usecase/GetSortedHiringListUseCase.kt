package com.example.fetchrewardsexercise.domain.usecase

import com.example.fetchrewardsexercise.core.util.Resource
import com.example.fetchrewardsexercise.domain.model.GroupedHiringList
import com.example.fetchrewardsexercise.domain.model.HiringList
import com.example.fetchrewardsexercise.domain.repository.FetchRewardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class GetSortedHiringListUseCase(private val fetchRewardsRepository: FetchRewardsRepository) {

    operator fun invoke(): Flow<Resource<List<GroupedHiringList>>> = flow {
        emit(Resource.Loading())
        try {
            val hiringList = fetchRewardsRepository.getHiringList()
            val groupedHiringList = getGroupedAndSortedHiringList(hiringList)
            emit(Resource.Success(groupedHiringList))
        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    private fun getGroupedAndSortedHiringList(hiringList: HiringList): List<GroupedHiringList> {
        return hiringList.items
            .filterNot { it.name.isNullOrEmpty() }
            .groupBy { it.listId }
            .toSortedMap()
            .map { (listId, items) -> GroupedHiringList(listId, items.sortedBy { it.itemNumber }) }
    }

}