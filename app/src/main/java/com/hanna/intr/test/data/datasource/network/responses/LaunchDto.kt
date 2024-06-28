package com.hanna.intr.test.data.datasource.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("date_utc") val dateUtc: String,
    @SerialName("success") val success: Boolean,
    @SerialName("details") val details: String?
)