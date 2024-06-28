package com.hanna.intr.test.data.repositories

import com.hanna.intr.test.domain.models.Launch

interface LaunchesRepository {

    suspend fun fetchAllLaunches(): Result<List<Launch>>

    suspend fun fetchLaunchById(id: String): Result<Launch>
}