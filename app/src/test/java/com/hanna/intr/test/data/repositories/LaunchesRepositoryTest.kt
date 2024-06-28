package com.hanna.intr.test.data.repositories

import com.hanna.intr.test.data.datasource.network.api.LaunchApi
import com.hanna.intr.test.data.datasource.network.responses.LaunchDto
import com.hanna.intr.test.domain.models.Launch
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LaunchesRepositoryTest {

    private lateinit var repository: LaunchesRepositoryImpl

    private val api: LaunchApi = mockk()

    @Before
    fun setUp() {// Given
        repository = LaunchesRepositoryImpl(api)
    }


    @Test
    fun `fetchAllLaunches throws an error, getAllLaunches() isFailure`() = runBlocking {
        coEvery { api.getAllLaunches() } throws (Exception("Testing error"))

        val result = repository.fetchAllLaunches()

        assertTrue(result.isFailure)
    }


    @Test
    fun `getAllLaunches throws an error, result message matched thrown message`() = runBlocking {
        coEvery { api.getAllLaunches() } throws (Exception("Testing error"))

        val result = repository.fetchAllLaunches()

        assertTrue(result.exceptionOrNull()?.message == "Testing error")
    }


    @Test
    fun `getAllLaunches returns successful result`() = runBlocking {
        coEvery { api.getAllLaunches() }.returns(listOf(LaunchDto("", "", "2024-03-21T01:10:00.000Z", false, "details")))

        val result = repository.fetchAllLaunches()

        assertTrue(result.isSuccess)
    }

    @Test
    fun `getAllLaunches returns expected data`() = runBlocking {
        coEvery { api.getAllLaunches() }.returns(listOf(LaunchDto("", "", "2007-03-21T01:10:00.000Z", false, "details")))

        val result = repository.fetchAllLaunches()

        assertEquals(result.getOrNull()?.get(0), Launch("", "", "21 March, 2007", false, "details"))
    }

    @Test
    fun `fetchLaunchById throws an error, fetchLaunchById() isFailure`() = runBlocking {
        coEvery { api.getLaunchById(any()) } throws (Exception("Testing error"))

        val result = repository.fetchLaunchById("3")

        assertTrue(result.isFailure)
    }


    @Test
    fun `fetchLaunchById throws an error, result message matched thrown message`() = runBlocking {
        coEvery { api.getLaunchById(any()) } throws (Exception("Testing error"))

        val result = repository.fetchLaunchById("3")

        assertTrue(result.exceptionOrNull()?.message == "Testing error")
    }


    @Test
    fun `fetchLaunchById returns successful result`() = runBlocking {
        coEvery { api.getLaunchById(any()) }.returns(LaunchDto("", "", "2024-03-21T01:10:00.000Z", false, "details"))

        val result = repository.fetchLaunchById("f")

        assertTrue(result.isSuccess)
    }

    @Test
    fun `fetchLaunchById returns expected data`() = runBlocking {
        coEvery { api.getLaunchById(any()) }.returns(LaunchDto("", "", "2007-03-21T01:10:00.000Z", false, "details"))

        val result = repository.fetchLaunchById("Hanna")

        assertEquals(Launch("", "", "21 March, 2007", false, "details"), result.getOrNull())
    }
}