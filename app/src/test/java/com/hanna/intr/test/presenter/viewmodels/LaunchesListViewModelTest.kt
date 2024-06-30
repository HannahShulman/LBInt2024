package com.hanna.intr.test.presenter.viewmodels

import com.hanna.intr.test.domain.models.Launch
import com.hanna.intr.test.domain.usecases.FetchAllLaunchesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DrawsViewModelTest {

    private val drawsList = listOf<Launch>(

    )

    private val fetchAllLaunchesUseCase: FetchAllLaunchesUseCase = mockk(relaxed = true)
    private lateinit var viewModel: LaunchesListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun `viewmodel init triggers fetchDraws`() = runTest {
        coEvery { fetchAllLaunchesUseCase() } returns Result.success(drawsList)

        // Recreate the viewModel to trigger init block
        viewModel = LaunchesListViewModel(fetchAllLaunchesUseCase)

        coVerify { fetchAllLaunchesUseCase() }
    }

    @Test
    fun `fetchDraws success, updates drawsListScreenUiState`() = runTest {

        coEvery { fetchAllLaunchesUseCase() } returns Result.success(drawsList)
        viewModel = LaunchesListViewModel(fetchAllLaunchesUseCase)

        viewModel.fetchAllLaunches()

        val state = viewModel.launchesListUiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(drawsList, state.launchesList)
        assertEquals(null, state.error)
    }

    @Test
    fun `fetchDraws failure updates drawsListScreenUiState`() = runTest {
        val errorMessage = "Error fetching draws"
        coEvery { fetchAllLaunchesUseCase() } returns Result.failure(Exception(errorMessage))
        viewModel = LaunchesListViewModel(fetchAllLaunchesUseCase)

        viewModel.fetchAllLaunches()

        val state = viewModel.launchesListUiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(null, state.launchesList)
        assertEquals(errorMessage, state.error)
    }
}
