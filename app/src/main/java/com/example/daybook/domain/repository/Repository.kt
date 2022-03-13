package com.example.daybook.domain.repository

import com.example.daybook.domain.entity.Event

interface Repository {

    fun getAllEvents():List<Event>
    suspend fun addEvent(eventName: String, eventDescription: String, date_start: String, date_finish:String)
}