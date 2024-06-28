package com.hanna.intr.test.data.datasource.network.api

import com.hanna.intr.test.data.datasource.network.responses.LaunchDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LaunchApi {
    @GET("v4/launches")
    suspend fun getLaunches(): List<LaunchDto>

    @GET("v4/launches/{Id}")
    suspend fun getLaunchById(@Path("Id") id: String): LaunchDto
}