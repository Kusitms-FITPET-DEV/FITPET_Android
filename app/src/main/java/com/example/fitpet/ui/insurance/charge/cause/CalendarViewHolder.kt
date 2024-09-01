package com.example.fitpet.ui.insurance.charge.cause

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemCalendarDateBinding
import com.example.fitpet.model.CalendarDate

class CalendarViewHolder(
    private val binding: ItemCalendarDateBinding,
    private val adapter: CalendarAdapter
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: CalendarDate, position: Int) {

        with (adapter) {
            binding.apply {
                item = data
                tvItemCalendarDate.isActivated = (position == selectedPosition)

                root.setOnClickListener {
                    onItemClickListener?.onItemClick(data, position)

                    if (selectedPosition != position) {
                        val previousPosition = selectedPosition
                        selectedPosition = position

                        notifyItemChanged(previousPosition)
                        notifyItemChanged(position)
                    }
                }
            }
        }

    }
}