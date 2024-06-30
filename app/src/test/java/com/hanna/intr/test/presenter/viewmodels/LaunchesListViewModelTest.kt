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
class LaunchesListViewModelTest {

    private val launchList = listOf<Launch>()

    private val fetchAllLaunchesUseCase: FetchAllLaunchesUseCase = mockk(relaxed = true)
    private lateinit var viewModel: LaunchesListViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `viewmodel init triggers fetchAllLaunches`() = runTest {
        coEvery { fetchAllLaunchesUseCase() } returns Result.success(launchList)

        // Recreate the viewModel to trigger init block
        viewModel = LaunchesListViewModel(fetchAllLaunchesUseCase)

        coVerify { fetchAllLaunchesUseCase() }
    }

    @Test
    fun `fetchAllLaunches success, updates launchesListUiState`() = runTest {

        coEvery { fetchAllLaunchesUseCase() } returns Result.success(launchList)
        viewModel = LaunchesListViewModel(fetchAllLaunchesUseCase)

        viewModel.fetchAllLaunches()

        val state = viewModel.launchesListUiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(launchList, state.launchesList)
        assertEquals(null, state.error)
    }

    @Test
    fun `fetchAllLaunches failure updates launchesListUiState`() = runTest {
        val errorMessage = "Error fetching launches"
        coEvery { fetchAllLaunchesUseCase() } returns Result.failure(Exception(errorMessage))
        viewModel = LaunchesListViewModel(fetchAllLaunchesUseCase)

        viewModel.fetchAllLaunches()

        val state = viewModel.launchesListUiState.first()
        assertEquals(false, state.isLoading)
        assertEquals(null, state.launchesList)
        assertEquals(errorMessage, state.error)
    }
}
