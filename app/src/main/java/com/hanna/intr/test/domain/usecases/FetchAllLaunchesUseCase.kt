package com.hanna.intr.test.domain.usecases

import com.hanna.intr.test.data.repositories.LaunchesRepository

class FetchAllLaunchesUseCase (val repository: LaunchesRepository) {

    suspend operator fun invoke()  = repository.fetchAllLaunches()
}