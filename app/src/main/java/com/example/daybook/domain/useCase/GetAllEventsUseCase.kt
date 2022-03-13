package com.example.daybook.domain.useCase

import com.example.daybook.domain.entity.Event
import com.example.daybook.domain.repository.Repository
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(private val repository: Repository){

    fun execute():List<Event>{
        return repository.getAllEvents()
    }
}