package com.example.daybook.presentation.eventList

import com.example.daybook.presentation.models.Event

interface EventClickListener {

    fun onItemClick(event: Event)
}