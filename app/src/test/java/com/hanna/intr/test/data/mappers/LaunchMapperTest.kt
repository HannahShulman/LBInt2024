package com.hanna.intr.test.data.mappers

import com.hanna.intr.test.data.datasource.network.responses.LaunchDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class LaunchMapperTest {

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_id_mapped_as_expected() {

        val response = LaunchDto(id = "id", name = "first launch", dateUtc = "2007-03-21T01:10:00.000Z", success = false, details = null)
        assertEquals("id", response.map().id)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_name_mapped_as_expected() {

        val response = LaunchDto(id = "id", name = "first launch", dateUtc = "2007-03-21T01:10:00.000Z", success = false, details = null)
        assertEquals("first launch", response.map().name)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_date_mapped_as_expected() {

        val response = LaunchDto(id = "id", name = "first launch", dateUtc = "2007-03-21T01:10:00.000Z", success = false, details = null)
        assertEquals("21 March, 2007", response.map().date)
    }

    @Test
    fun GIVEN_drawResponse_WHEN_mapped_THEN_success_mapped_as_expected() {

        val response = LaunchDto(id = "id", name = "first launch", dateUtc = "2007-03-21T01:10:00.000Z", success = true, details = null)

        assertEquals(true, response.map().success)
    }

    @Test
    fun GIVEN_drawResponse_with_details_null_WHEN_mapped_THEN_details_mapped_as_expected() {

        val response = LaunchDto(id = "id", name = "first launch", dateUtc = "2007-03-21T01:10:00.000Z", success = false, details = null)

        assertEquals("Sorry, no details!", response.map().details)
    }

    @Test
    fun GIVEN_drawResponse_with_details_empty_WHEN_mapped_THEN_details_mapped_as_expected() {

        val response = LaunchDto(id = "id", name = "first launch", dateUtc = "2007-03-21T01:10:00.000Z", success = false, details = "")

        assertEquals("Sorry, no details!", response.map().details)
    }

    @Test
    fun GIVEN_drawResponse_with_details_not_empty_WHEN_mapped_THEN_details_mapped_as_expected() {

        val response = LaunchDto(id = "id", name = "first launch", dateUtc = "2007-03-21T01:10:00.000Z", success = false, details = "details found")

        assertEquals("details found", response.map().details)
    }
}