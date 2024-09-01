package com.example.fitpet.ui.insurance.charge.cause

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemCalendarDateBinding
import com.example.fitpet.model.CalendarDate

class CalendarViewHolder(
    private val binding: ItemCalendarDateBinding
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: CalendarDate) {
        binding.item = data
    }
}