package com.example.daybook.domain.useCase

import com.example.daybook.domain.repository.Repository
import javax.inject.Inject

class AddEventUseCase @Inject constructor(private val repository: Repository){

    suspend fun execute(eventName: String, eventDescription: String, date_start: String, date_finish:String){
        repository.addEvent(eventName,eventDescription, date_start, date_finish)
    }
}