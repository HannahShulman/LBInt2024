package com.hanna.intr.test.data.repositories

import com.hanna.intr.test.data.datasource.network.api.LaunchApi
import com.hanna.intr.test.data.mappers.map
import com.hanna.intr.test.domain.models.Launch

class LaunchesRepositoryImpl(private val api: LaunchApi) : LaunchesRepository {

    override suspend fun fetchAllLaunches(): Result<List<Launch>> {
        return runCatching { api.getAllLaunches().map { it.map() } }
    }

    override suspend fun fetchLaunchById(id: String): Result<Launch> {
        return runCatching { api.getLaunchById(id).map() }
    }
}