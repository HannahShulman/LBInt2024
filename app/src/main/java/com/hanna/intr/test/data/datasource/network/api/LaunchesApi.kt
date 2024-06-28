package com.hanna.intr.test.data.datasource.network.api

import com.hanna.intr.test.data.datasource.network.responses.LaunchDto
import retrofit2.http.GET

interface LaunchApi {
    @GET("v4/launches")
    suspend fun getLaunches(): List<LaunchDto>
}