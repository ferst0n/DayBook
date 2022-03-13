package com.example.daybook.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.daybook.presentation.DataViewModel
import com.example.daybook.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailsFragment(): Fragment() {

    private val dataViewModel: DataViewModel by activityViewModels()
    private lateinit var title:TextView
    private lateinit var dateStart:TextView
    private lateinit var dateFinish:TextView
    private lateinit var description:TextView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_event_details, container, false)

        title = view.findViewById(R.id.title)
        dateStart = view.findViewById(R.id.date_start)
        dateFinish = view.findViewById(R.id.date_finish)
        description = view.findViewById(R.id.description)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataViewModel.data.observe(viewLifecycleOwner) { event ->
            title.text = event.name
            dateStart.text = event.date_start + " " + event.time_start
            dateFinish.text = event.date_finish + " " + event.time_finish
            description.text = event.description
        }
    }

}