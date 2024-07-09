package com.example.fetchrewardsexercise.domain.usecase

import com.example.fetchrewardsexercise.core.util.Resource
import com.example.fetchrewardsexercise.domain.model.GroupedHiringList
import com.example.fetchrewardsexercise.domain.model.HiringList
import com.example.fetchrewardsexercise.domain.model.HiringListItem
import com.example.fetchrewardsexercise.domain.repository.FetchRewardsRepository
import com.example.fetchrewardsexercise.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetSortedHiringListUseCaseTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val fetchRewardsRepository: FetchRewardsRepository = mockk()
    lateinit var subject: GetSortedHiringListUseCase

    @Before
    fun setUp() {
        initializeSubject()
    }

    @Test
    fun `first emission is loading when invoked`() = runTest {
        val result = subject()
        val resource = result.first() as? Resource.Loading
        assertTrue(resource is Resource.Loading)
    }

    @Test
    fun `verify properly sorted and filtered data is returned on success`() = runTest {
        val mockHiringList = HiringList(
            listOf(
                HiringListItem(1, 1, "Item 2"),
                HiringListItem(2, 1, "Item 1"),
                HiringListItem(3, 2, null) // Filtered out
            )
        )
        val expectedGroupedList = listOf(
            GroupedHiringList(
                listId = 1,
                items = listOf(
                    HiringListItem(id = 2, listId = 1, name = "Item 1"),
                    HiringListItem(id = 1, listId = 1, name = "Item 2")
                )
            )
        )
        coEvery { fetchRewardsRepository.getHiringList() } returns mockHiringList
        val result = subject()
        val resource = result.last() as? Resource.Success
        assertTrue(resource is Resource.Success)
        assertEquals(expectedGroupedList, resource?.data)
    }

    @Test
    fun `verify failure with message is emitted`() = runTest {
        val errorMessage = "Unexpected error"
        coEvery { fetchRewardsRepository.getHiringList() } throws Exception(errorMessage)
        val result = subject()
        val resource = result.last() as? Resource.Failure
        assertTrue(resource is Resource.Failure)
        assertEquals(errorMessage, resource?.message)
    }


    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun initializeSubject() {
        subject = GetSortedHiringListUseCase(fetchRewardsRepository)
    }
}