package com.example.fetchrewardsexercise.core.di

import com.example.fetchrewardsexercise.data.remote.api.FetchRewardsApi
import com.example.fetchrewardsexercise.data.remote.repository.FetchRewardsRepositoryImpl
import com.example.fetchrewardsexercise.domain.repository.FetchRewardsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideFetchRewardsApi(client: OkHttpClient): FetchRewardsApi {
        return Retrofit.Builder().baseUrl(FetchRewardsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideFetchRewardsRepository(fetchRewardsApi: FetchRewardsApi): FetchRewardsRepository {
        return FetchRewardsRepositoryImpl(fetchRewardsApi)
    }

}