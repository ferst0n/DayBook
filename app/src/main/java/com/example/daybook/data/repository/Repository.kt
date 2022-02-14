package com.example.daybook.data.repository

import com.example.daybook.data.localDataSource.LocalDataSource
import com.example.daybook.data.model.EventDB
import com.example.daybook.presentation.models.Event
import org.bson.types.ObjectId
import javax.inject.Inject

class Repository @Inject constructor(private val localDataSource: LocalDataSource) {


    fun getAllEvents():List<Event> {

            return localDataSource.getAllEvents()

    }

    suspend fun addEvent(eventName: String, eventDescription: String, date_start: String, date_finish:String){

        val event = EventDB()
        event._id = ObjectId()
        event.name = eventName
        event.description = eventDescription
        event.date_start = date_start
        event.date_finish = date_finish

        localDataSource.addEvent(event)
    }


}