package com.hanna.intr.test.domain.usecases

import com.hanna.intr.test.data.repositories.LaunchesRepository
import javax.inject.Inject

class FetchAllLaunchesUseCase @Inject constructor(val repository: LaunchesRepository) {

    suspend operator fun invoke()  = repository.fetchAllLaunches()
}