package com.example.hobbyapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp.Domain.ScheduleModel
import com.example.hobbyapp.R

class ScheduleAdapter(private val schedule: List<ScheduleModel>) :
    RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    class ScheduleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val timeText = view.findViewById<TextView>(R.id.scheduleTime)
        val subjectText = view.findViewById<TextView>(R.id.scheduleSubject)
        val dayText = view.findViewById<TextView>(R.id.scheduleDay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item = schedule[position]
        holder.dayText.text = item.day
        holder.timeText.text = item.time
        holder.subjectText.text = item.subject
    }

    override fun getItemCount() = schedule.size
}
