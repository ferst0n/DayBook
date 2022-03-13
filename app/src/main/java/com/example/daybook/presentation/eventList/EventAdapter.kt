package com.example.daybook.presentation.eventList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.daybook.R
import com.example.daybook.domain.entity.Event

class EventAdapter(private var eventClickListener: EventClickListener)
    :RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    var mEvents: ArrayList<Event> = ArrayList()

    fun setData(newEvents: List<Event>){
        mEvents.clear()
        mEvents.addAll(newEvents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.text_row_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Event = mEvents[position])

        holder.itemView.setOnClickListener {
            eventClickListener.onItemClick(mEvents[position])
        }

    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val eventName: TextView
        val date:TextView

        fun bind(Event: Event){
            eventName.text =Event.name
            date.text = Event.time_start+" - "+Event.time_finish
        }

        init {
            eventName = view.findViewById(R.id.event_name)
            date = view.findViewById(R.id.event_date)

        }
    }
}