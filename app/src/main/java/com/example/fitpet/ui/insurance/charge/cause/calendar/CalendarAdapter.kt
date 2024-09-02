package com.example.fitpet.ui.insurance.charge.cause.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fitpet.databinding.ItemCalendarDateBinding
import com.example.fitpet.model.CalendarDate
import com.example.fitpet.util.ItemDiffCallback

class CalendarAdapter(var selectedPosition: Int) : ListAdapter<CalendarDate, CalendarViewHolder>(
    ItemDiffCallback<CalendarDate>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.date == newItem.date }
    )
) {

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: CalendarDate, position: Int)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding =
            ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.onBind(currentList[position], position)
    }
}