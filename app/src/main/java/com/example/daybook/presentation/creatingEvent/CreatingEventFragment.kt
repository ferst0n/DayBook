package com.example.daybook.presentation.creatingEvent

import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
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
import com.example.daybook.Utils.getFormattedDateView
import com.example.daybook.presentation.pikers.DatePickerFragment
import com.example.daybook.presentation.pikers.TimePickerFragment
import dagger.hilt.android.AndroidEntryPoint


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

    companion object{
        val TRANSITION_DELAY: Long = 500
    }

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
            showTimePickerStartDialog()
        }
        pickTimeFinishButton.setOnClickListener {
            showTimePickerFinishDialog()
        }
        pickDateButton.setOnClickListener {
            showDatePickerDialog()
        }

        dataViewModel.getDate().observe(viewLifecycleOwner){date ->
            showPickedDate(date)
        }

        dataViewModel.getTimeStart().observe(viewLifecycleOwner){timeStart ->
            showPickedTimeStart(timeStart)
        }

        dataViewModel.getTimeFinish().observe(viewLifecycleOwner){timeFinish ->
            showPickedTimeFinish(timeFinish)
        }

        addEventButton.setOnClickListener {
            addEvent()
        }

    }

    override fun onResume() {
        super.onResume()
        backPressedHandle()
    }

    private fun backPressedHandle(){
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v, keyCode, event ->
            if (event.action === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                dataViewModel.setDate("")
                dataViewModel.setTimeStart("")
                dataViewModel.setTimeFinish("")
                navController.navigate(R.id.action_creatingEventFragment_to_calendarFragment)

                true
            } else false
        }
    }

    private fun showPickedDate(date:String){
        if (dataViewModel.getDate().value != "") {

            pickDateButton.text = getFormattedDateView(date)

        }else { pickDateButton.text = getString(R.string.pick_date) }
    }

    private fun showPickedTimeStart(timeStart:String){
        if (dataViewModel.getTimeStart().value != "") {

            pickTimeStartButton.text = timeStart

        } else{pickTimeStartButton.text = getString(R.string.pick_time_start)}
    }

    private fun showPickedTimeFinish(timeFinish:String){
        if (dataViewModel.getTimeFinish().value != ""){

            pickTimeFinishButton.text = timeFinish

        }else{pickTimeFinishButton.text = getString(R.string.pick_time_finish)}
    }

    private fun addEvent(){
        var eventName = titleEditText.text.toString()
        var eventDescription = descriptionEditText.text.toString()
        var eventDate = dataViewModel.getDate().value.toString()
        var eventTimeStart = dataViewModel.getTimeStart().value.toString()
        var eventTimeFinish = dataViewModel.getTimeFinish().value.toString()

        if (eventName != "" && eventDate != "" && eventTimeStart != "" && eventTimeFinish != ""){

            creatingEventViewModel.addEvent(eventName, eventDescription,
                eventDate, eventTimeStart, eventTimeFinish
            )

            Toast.makeText(context,R.string.add_event, Toast.LENGTH_SHORT).show()

            dataViewModel.setDate("")
            dataViewModel.setTimeStart("")
            dataViewModel.setTimeFinish("")
            Handler().postDelayed({
                navController.navigate(R.id.action_creatingEventFragment_to_calendarFragment)
            }, TRANSITION_DELAY)

        }else{

            Toast.makeText(context,R.string.fill_field, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showTimePickerStartDialog() {

        TimePickerFragment().show(childFragmentManager,R.string.time_picker_start.toString())
    }

    private fun showTimePickerFinishDialog() {

        TimePickerFragment().show(childFragmentManager, R.string.time_picker_finish.toString())
    }

    private fun showDatePickerDialog() {

        DatePickerFragment().show(childFragmentManager, R.string.date_picker.toString())
    }




}