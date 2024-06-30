package com.hanna.intr.test.domain.usecases

import com.hanna.intr.test.data.repositories.LaunchesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class FetchLaunchByIdUseCaseTest {

    private lateinit var useCase: FetchLaunchByIdUseCase

    private val repo: LaunchesRepository = mockk()

    @Before
    fun setUp() {
        useCase = FetchLaunchByIdUseCase(repo)
    }

    @Test
    fun when_usecase_invoked_then_repo_calls_fetchById() = runTest {

        coEvery { repo.fetchLaunchById(any()) } returns Result.failure(Throwable("Error message"))

        useCase("34")

        coVerify { repo.fetchLaunchById("34") }
    }
}