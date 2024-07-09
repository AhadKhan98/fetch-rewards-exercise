package com.example.fetchrewardsexercise.core.di

import com.example.fetchrewardsexercise.domain.repository.FetchRewardsRepository
import com.example.fetchrewardsexercise.domain.usecase.GetSortedHiringListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideSortedHiringList(fetchRewardsRepository: FetchRewardsRepository): GetSortedHiringListUseCase {
        return GetSortedHiringListUseCase(fetchRewardsRepository)
    }


}