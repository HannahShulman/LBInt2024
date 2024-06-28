package com.hanna.intr.test.domain.usecases

import com.hanna.intr.test.data.repositories.LaunchesRepository
import javax.inject.Inject


class FetchLaunchByIdUseCase @Inject constructor(val repository: LaunchesRepository) {

    suspend operator fun invoke(id: String) = repository.fetchLaunchById(id)
}