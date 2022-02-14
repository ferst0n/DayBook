package com.example.daybook.presentation.eventList


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.daybook.data.repository.Repository
import com.example.daybook.presentation.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class EventListViewModel @Inject constructor(private val repository: Repository):ViewModel() {


    private val _events = MutableLiveData<List<Event>>()
    val events = _events


    fun getAllEvents() {

        val eventList = repository.getAllEvents()
        events.value = eventList

    }
}