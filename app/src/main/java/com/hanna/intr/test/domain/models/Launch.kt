package com.hanna.intr.test.domain.models

data class Launch(
    val name: String,
    val dateUtc: String,
    val success: Boolean?,
    val details: String?
)