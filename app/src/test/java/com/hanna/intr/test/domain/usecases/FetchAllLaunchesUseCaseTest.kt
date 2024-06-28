package com.hanna.intr.test.domain.usecases

import com.hanna.intr.test.data.repositories.LaunchesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class FetchAllLaunchesUseCaseTest {

    private lateinit var useCase: FetchAllLaunchesUseCase

    private val repo: LaunchesRepository = mockk()

    @Before
    fun setUp() {
        useCase = FetchAllLaunchesUseCase(repo)
    }


    @Test
    fun when_usecase_invoked_then_repo_calls_fetchAll() = runTest {

        coEvery { repo.fetchAllLaunches() } returns Result.success(listOf())

        useCase()

        coVerify { repo.fetchAllLaunches() }
    }

}