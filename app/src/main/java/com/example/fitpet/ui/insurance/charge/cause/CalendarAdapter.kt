package com.example.fitpet.ui.insurance.charge.cause

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fitpet.databinding.ItemCalendarDateBinding
import com.example.fitpet.model.CalendarDate
import com.example.fitpet.util.ItemDiffCallback

class CalendarAdapter : ListAdapter<CalendarDate, CalendarViewHolder>(
    ItemDiffCallback<CalendarDate>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.date == newItem.date }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding =
            ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}