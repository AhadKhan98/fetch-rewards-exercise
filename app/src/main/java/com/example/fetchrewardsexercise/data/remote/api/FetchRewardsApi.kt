package com.example.fetchrewardsexercise.data.remote.api

import com.example.fetchrewardsexercise.data.remote.dto.HiringListDto
import retrofit2.http.GET


interface FetchRewardsApi {

    @GET("hiring.json")
    suspend fun hiringList(): List<HiringListDto>

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }

}