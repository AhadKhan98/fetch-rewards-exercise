package com.example.fetchrewardsexercise.data.remote.repository

import com.example.fetchrewardsexercise.data.remote.api.FetchRewardsApi
import com.example.fetchrewardsexercise.data.remote.mapper.mapToDomainModel
import com.example.fetchrewardsexercise.domain.model.HiringList
import com.example.fetchrewardsexercise.domain.repository.FetchRewardsRepository

class FetchRewardsRepositoryImpl(private val fetchRewardsApi: FetchRewardsApi) :
    FetchRewardsRepository {

    override suspend fun getHiringList(): HiringList {
        val hiringListDto = fetchRewardsApi.hiringList()
        return hiringListDto.mapToDomainModel()
    }
}