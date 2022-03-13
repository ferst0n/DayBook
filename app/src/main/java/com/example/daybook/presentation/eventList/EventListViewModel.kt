package com.example.daybook.presentation.eventList


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.daybook.domain.entity.Event
import com.example.daybook.domain.useCase.GetAllEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class EventListViewModel @Inject constructor(private val getAllEventsUseCase: GetAllEventsUseCase) :ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events = _events

    fun getAllEvents() {

        val eventList = getAllEventsUseCase.execute()
        events.value = eventList

    }
}