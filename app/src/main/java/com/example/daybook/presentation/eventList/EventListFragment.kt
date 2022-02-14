package com.example.daybook.presentation.eventList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.applandeo.materialcalendarview.CalendarView
import com.example.daybook.presentation.DataViewModel
import com.example.daybook.presentation.EventAdapter
import com.example.daybook.presentation.EventClickListener
import com.example.daybook.R
import com.example.daybook.Utils.getFormattedDate
import com.example.daybook.Utils.getFormattedHour
import com.example.daybook.Utils.getFormattedMinute
import com.example.daybook.presentation.models.Event
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class EventListFragment: Fragment(), EventClickListener {

    private lateinit var mCalendarView: CalendarView
    private lateinit var mEventListRecycler: RecyclerView
    private var mEvents: ArrayList<Event> = ArrayList()
    private lateinit var mAdapter: EventAdapter
    private lateinit var navController: NavController
    private val dataViewModel: DataViewModel by activityViewModels()
    private lateinit var floatingActionButton: FloatingActionButton
    private val eventListViewModel:EventListViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_event_list, container, false)

        mCalendarView = view.findViewById(R.id.calendarView)
        mEventListRecycler = view.findViewById(R.id.evenList)
        floatingActionButton = view.findViewById(R.id.floating_action_button)

        eventListViewModel.getAllEvents()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.shape))
        mEventListRecycler.addItemDecoration(dividerItemDecoration)

        mAdapter = EventAdapter(this@EventListFragment)
        mEventListRecycler.setHasFixedSize(true)
        mEventListRecycler.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mEventListRecycler.adapter = mAdapter


        eventListViewModel.events.observe(activity as LifecycleOwner, {

            val currentDate = Date()
            mEvents.clear()
            for (event in it){
                if (event.date_start == getFormattedDate(currentDate)!!){

                    mEvents.add(event)

                }
            }

            mAdapter.setData(mEvents.sortedWith(compareBy({ getFormattedHour(it.time_start)}, { getFormattedMinute(it.time_start)})))

        })


        mCalendarView.setOnDayClickListener { selectedDay ->


            var selectedDate: Date = selectedDay.calendar.time


            mEvents.clear()

            for (event in eventListViewModel.events.value!!){

                if (event.date_start == getFormattedDate(selectedDate)!!){

                    mEvents.add(event)

                }
            }

            mAdapter.setData(mEvents.sortedWith(compareBy({ getFormattedHour(it.time_start)}, { getFormattedMinute(it.time_start)})))
            mAdapter.notifyDataSetChanged()

        }

        floatingActionButton.setOnClickListener {
            navController.navigate(R.id.action_calendarFragment_to_creatingEventFragment)
        }


    }
    

    override fun onItemClick(event: Event) {
        dataViewModel.data.value = event
        navController.navigate(R.id.action_calendarFragment_to_eventDetailsFragment)
    }


}