package com.example.daybook.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daybook.domain.entity.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(): ViewModel() {

    val data: MutableLiveData<Event> by lazy{
        MutableLiveData()
    }

    private val date: MutableLiveData<String> by lazy{
        MutableLiveData()
    }

    private val timeStart: MutableLiveData<String> by lazy{
        MutableLiveData()
    }

    private val timeFinish: MutableLiveData<String> by lazy{
        MutableLiveData()
    }

    fun getDate(): LiveData<String> {
        return date
    }

    fun setDate(selectedDate: String){
        date.value = selectedDate
    }

    fun getTimeStart(): LiveData<String> {
        return timeStart
    }

    fun setTimeStart(selectedTimeStart: String){
        timeStart.value = selectedTimeStart
    }

    fun getTimeFinish(): LiveData<String> {
        return timeFinish
    }

    fun setTimeFinish(selectedTimeFinish: String){
        timeFinish.value = selectedTimeFinish
    }

}