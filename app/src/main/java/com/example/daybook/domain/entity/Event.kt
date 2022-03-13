package com.example.daybook.domain.entity



data class Event(
    val id:String,
    val date_start:String,
    val date_finish:String,
    val time_start: String,
    val time_finish:String,
    val name:String,
    val descriotion:String?
)
