package com.hanna.intr.test.domain.models

data class Launch(
    val id: String,
    val name: String,
    val date: String,
    val success: Boolean,
    val details: String
)