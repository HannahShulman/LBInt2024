package com.hanna.intr.test.data.repositories

import com.hanna.intr.test.data.datasource.db.LaunchDao
import com.hanna.intr.test.data.datasource.network.api.LaunchApi
import com.hanna.intr.test.data.mappers.map
import com.hanna.intr.test.domain.models.Launch

/**
 * Launches repository impl
 *
 * This class is responsible for gathering the correct data that is to be sent to the domain layer.
 * The mechanism works as follows:
 * -First fetch data from the server
 * -Then insert into room/persistence,
 * -Fetch the data from the single source of truth which in this case is room.
 *
 * This enables a decent offline experience, and will prevent issues, if the server and persistence may be the source if truth.
 * In this way, once the call of fetch all is successful, we will never have an empty state, unless the user clears cache.
 *
 * @property api
 * @property launchDao
 * @constructor Create empty Launches repository impl
 */
class LaunchesRepositoryImpl(private val api: LaunchApi, private val launchDao: LaunchDao) : LaunchesRepository {

    /**
     * Fetch all launches
     *
     * @return the latest version and data that has been received from the server and cached localley.
     */
    override suspend fun fetchAllLaunches(): Result<List<Launch>> {
        return runCatching {
            val list = api.getAllLaunches().map { it.map() }
            launchDao.insertLaunches(list)
            launchDao.getAllLaunches()
        }
    }

    /**
     * Fetch launch by id
     *
     * With the logic of this app, there isnt really a need to fetch from the server, since all details have been fetched when going throu the full
     * list page,
     * However, in order to support deep linking, fetching first from the server, will ensure we have the upto date data (spesifically this data,
     * would change, but did want to show my school of thoughts:) )
     *
     * @param id
     * @return The launch details that have been requested.
     */
    override suspend fun fetchLaunchById(id: String): Result<Launch> {
        return runCatching {
            val mappedResponse = api.getLaunchById(id).map()
            launchDao.insertLaunch(mappedResponse)
            launchDao.getLaunchById(id)
        }
    }
}