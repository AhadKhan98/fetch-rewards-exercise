package com.example.fetchrewardsexercise.presentation.screen.hiring_list


import com.example.fetchrewardsexercise.core.util.Resource
import com.example.fetchrewardsexercise.domain.model.GroupedHiringList
import com.example.fetchrewardsexercise.domain.model.HiringListItem
import com.example.fetchrewardsexercise.domain.usecase.GetSortedHiringListUseCase
import com.example.fetchrewardsexercise.util.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HiringListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val sortedHiringListUseCase: GetSortedHiringListUseCase = mockk()
    private lateinit var subject: HiringListViewModel


    @Before
    fun setUp() {
        every { sortedHiringListUseCase.invoke() } returns flowOf(Resource.Loading())
        initializeSubject()
    }

    @Test
    fun `verify initial state should be loading`() {
        val state = subject.uiState.value
        assertTrue(state.isLoading)
        assertNull(state.groupedHiringList)
    }


    @Test
    fun `fetch success updates state with data`() {
        val mockData = listOf(GroupedHiringList(1, listOf(HiringListItem(1, 1, "John Doe"))))
        val successResource = Resource.Success(mockData)
        every { sortedHiringListUseCase.invoke() } returns flowOf(successResource)
        initializeSubject()
        val state = subject.uiState.value
        assertFalse(state.isLoading)
        assertEquals(mockData, state.groupedHiringList)
    }

    @Test
    fun `fetch failure updates state with error`() {
        val errorMessage = "Network Error"
        every { sortedHiringListUseCase.invoke() } returns flowOf(Resource.Failure(errorMessage))
        initializeSubject()
        val state = subject.uiState.value
        assertFalse(state.isLoading)
        assertNull(state.groupedHiringList)
        assertEquals(true, state.errorState.hasError)
        assertEquals(errorMessage, state.errorState.errorMessage)
    }

    @Test
    fun `verify onRetryFetch makes network call again`() {
        subject.uiState.value.actions.onRetryFetch()
        verify(exactly = 2) { sortedHiringListUseCase() } // One call from init and one from retry function
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun initializeSubject() {
        subject = HiringListViewModel(sortedHiringListUseCase)
    }
}