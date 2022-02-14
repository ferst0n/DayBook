package com.example.daybook.presentation.creatingEvent

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.daybook.presentation.DataViewModel
import com.example.daybook.R
import com.example.daybook.presentation.pikers.DatePickerFragment
import com.example.daybook.presentation.pikers.TimePickerFragment
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Timestamp


@AndroidEntryPoint
class  CreatingEventFragment:Fragment() {


    private lateinit var pickTimeStartButton:Button
    private lateinit var pickTimeFinishButton:Button
    private lateinit var pickDateButton:Button
    private lateinit var addEventButton:Button
    private val creatingEventViewModel: CreatingEventViewModel by viewModels()
    private lateinit var titleEditText:EditText
    private lateinit var descriptionEditText:EditText
    private lateinit var navController: NavController
    private val dataViewModel: DataViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_creating_event, container, false)

        pickTimeStartButton = view.findViewById(R.id.pick_timeStart_button)
        pickTimeFinishButton = view.findViewById(R.id.pick_timeFinish_button)
        pickDateButton = view.findViewById(R.id.pick_date_button)
        addEventButton = view.findViewById(R.id.add_event_button)
        titleEditText = view.findViewById(R.id.title_edit_text)
        descriptionEditText = view.findViewById(R.id.description_edit_text)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        pickTimeStartButton.setOnClickListener {
            showTimePickerStartDialog(it)
        }
        pickTimeFinishButton.setOnClickListener {
            showTimePickerFinishDialog(it)
        }
        pickDateButton.setOnClickListener {
            showDatePickerDialog(it)
        }


        addEventButton.setOnClickListener {

            var eventName = titleEditText.text.toString()
            var eventDescription = descriptionEditText.text.toString()
            var eventDate = dataViewModel.date.value
            var eventTimeStart = dataViewModel.timeStart.value
            var eventTimeFinish = dataViewModel.timeFinish.value

            if (eventName != "" && eventDate != "" && eventTimeStart != "" && eventTimeFinish != ""){

                var timeStampStart = Timestamp.valueOf(eventDate+" "+eventTimeStart).time
                var timeStampFinish = Timestamp.valueOf(eventDate+" "+eventTimeFinish).time

                creatingEventViewModel.addNote(eventName, eventDescription,
                    timeStampStart.toString(), timeStampFinish.toString()
                )

                Toast.makeText(context,"Задача добавлена", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    navController.navigate(R.id.action_creatingEventFragment_to_calendarFragment)
                }, 500)

            }else{

                Toast.makeText(context,"Заполните все поля", Toast.LENGTH_SHORT).show()
            }

        }

    }


    fun showTimePickerStartDialog(v: View) {

        fragmentManager?.let { TimePickerFragment().show(it, "timePickerStart") }
    }

    fun showTimePickerFinishDialog(v: View) {

        fragmentManager?.let { TimePickerFragment().show(it, "timePickerFinish") }
    }

    fun showDatePickerDialog(v: View) {

        fragmentManager?.let { DatePickerFragment().show(it, "datePicker") }
    }

    override fun onDestroy() {
        super.onDestroy()
        dataViewModel.date.value = ""
        dataViewModel.timeStart.value = ""
        dataViewModel.timeFinish.value = ""
    }

    override fun onResume() {
        super.onResume()
        dataViewModel.date.value = ""
        dataViewModel.timeStart.value = ""
        dataViewModel.timeFinish.value = ""
    }


}