package com.example.daybook.presentation.eventList

import com.example.daybook.domain.entity.Event

interface EventClickListener {

    fun onItemClick(event: Event)
}