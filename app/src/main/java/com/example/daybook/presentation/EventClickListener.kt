package com.example.daybook.presentation

import com.example.daybook.presentation.models.Event

interface EventClickListener {

    fun onItemClick(event: Event)
}