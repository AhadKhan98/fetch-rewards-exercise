package com.example.fetchrewardsexercise.domain.repository

import com.example.fetchrewardsexercise.domain.model.HiringList

interface FetchRewardsRepository {

    suspend fun getHiringList(): HiringList

}