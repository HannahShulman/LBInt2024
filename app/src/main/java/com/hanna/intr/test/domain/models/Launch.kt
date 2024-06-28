package com.hanna.intr.test.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Launch(
    @PrimaryKey val id: String,
    val name: String,
    val date: String,
    val success: Boolean,
    val details: String
)