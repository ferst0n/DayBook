package com.example.daybook.presentation.creatingEvent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daybook.Utils.getFormattedTime
import com.example.daybook.domain.useCase.AddEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class CreatingEventViewModel @Inject constructor( private val addEventUseCase: AddEventUseCase) : ViewModel() {

    fun addEvent(eventName: String,
                 eventDescription:String,
                 date:String,
                 time_start:String,
                 time_finish:String) {

        viewModelScope.launch(){

            var timeStampStart = Timestamp.valueOf(date+" "+getFormattedTime(time_start)).time.toString()
            var timeStampFinish = Timestamp.valueOf(date+" "+getFormattedTime(time_finish)).time.toString()
            addEventUseCase.execute(eventName,eventDescription,timeStampStart,timeStampFinish)
        }

    }

}