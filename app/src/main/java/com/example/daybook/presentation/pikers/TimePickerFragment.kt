package com.example.daybook.presentation.pikers

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.daybook.presentation.DataViewModel
import java.util.*


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private val dataViewModel: DataViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {

        var time = hourOfDay.toString() + ":" + minute.toString() + ":" + "0"

        Log.d("selectedTime", time)
        Log.d("TAG", tag.toString())

        if (tag.toString() == "timePickerStart"){

            dataViewModel.timeStart.value = time

        }else if (tag.toString() == "timePickerFinish"){

            dataViewModel.timeFinish.value = time

        }

    }


}