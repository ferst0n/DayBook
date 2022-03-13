package com.example.daybook.domain.useCase

import com.example.daybook.domain.entity.Event
import com.example.daybook.domain.repository.Repository
import org.junit.Assert.assertEquals
import org.junit.Test


class TestRepository:Repository{

    override fun getAllEvents(): List<Event> {
        return listOf(Event("1","13-03-2022","13-03-2022","16:45",
            "17:54","shop","buy: bread,milk,eggs,potato"))
    }

    override suspend fun addEvent(
        eventName: String,
        eventDescription: String,
        date_start: String,
        date_finish: String
    ) {
        TODO("Not yet implemented")
    }

}

class GetAllEventsUseCaseTest {

    @Test
    fun shouldReturnCorrectData() {
        val testRepository = TestRepository()
        val useCase = GetAllEventsUseCase(repository = testRepository)
        val actual = useCase.execute()
        val expected = listOf(Event("1","13-03-2022","13-03-2022","16:45",
            "17:54","shop","buy: bread,milk,eggs,potato"))
        assertEquals(expected, actual)
    }
}