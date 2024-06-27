package com.hanna.intr.test.data.mappers

import com.hanna.intr.test.data.datasource.network.responses.LaunchDto
import com.hanna.intr.test.domain.models.Launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun LaunchDto.map(): Launch {

    fun formatDateString(inputDate: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        val date: Date = inputFormatter.parse(inputDate)!!
        return outputFormatter.format(date)
    }

    return Launch(
        id = id,
        name = name,
        date = formatDateString(dateUtc),
        success = success,
        details = details.orEmpty().takeIf { it.isNotEmpty() } ?: "Sorry, no details!"
    )
}