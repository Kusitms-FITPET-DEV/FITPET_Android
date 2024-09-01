package com.example.fitpet.ui.insurance.charge.cause

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemCalendarDateBinding
import com.example.fitpet.model.CalendarDate
import com.example.fitpet.util.ItemDiffCallback

class CalendarAdapter : ListAdapter<CalendarDate, CalendarAdapter.CalendarViewHolder>(
    ItemDiffCallback<CalendarDate>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.date == newItem.date }
    )
) {

    private var onItemClickListener: OnItemClickListener? = null
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    interface OnItemClickListener {
        fun onItemClick(item: CalendarDate, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class CalendarViewHolder(
        private val binding: ItemCalendarDateBinding,
//        private var selectedPosition: Int,
//        private var onItemClickListener: OnItemClickListener?,
//        private val adapter: CalendarAdapter,
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: CalendarDate) {
            binding.item = data

            if (selectedPosition == absoluteAdapterPosition) {
                changeColor(binding, true)
            } else {
                changeColor(binding, false)
            }

            if (onItemClickListener != null) {
                binding.root.setOnClickListener {
                    onItemClickListener?.onItemClick(data, absoluteAdapterPosition)
                    if (selectedPosition != absoluteAdapterPosition) {
                        changeColor(binding, true)
                        notifyItemChanged(selectedPosition)
                        selectedPosition = absoluteAdapterPosition
                    }
                }
            }
        }

        private fun changeColor(
            binding: ItemCalendarDateBinding,
            selected: Boolean
        ) {
            when (selected) {
                true -> binding.tvItemCalendarDate.setBackgroundResource(R.drawable.bg_oval_filled_point_blue)
                false -> binding.tvItemCalendarDate.setBackgroundResource(android.R.color.transparent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding =
            ItemCalendarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}