package com.hanna.intr.test.domain.usecases

import com.hanna.intr.test.data.repositories.LaunchesRepository

class FetchLaunchByIdUseCase (val repository: LaunchesRepository) {

    suspend operator fun invoke(id: String) = repository.fetchLaunchById(id)
}