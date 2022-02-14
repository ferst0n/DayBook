package com.example.daybook.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daybook.presentation.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(): ViewModel() {

    val data: MutableLiveData<Event> by lazy{
        MutableLiveData()
    }

    val date: MutableLiveData<String> by lazy{
        MutableLiveData()
    }

    val timeStart: MutableLiveData<String> by lazy{
        MutableLiveData()
    }

    val timeFinish: MutableLiveData<String> by lazy{
        MutableLiveData()
    }
    

}