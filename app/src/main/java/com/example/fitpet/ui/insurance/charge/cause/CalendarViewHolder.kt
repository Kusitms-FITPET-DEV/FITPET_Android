package com.example.fitpet.ui.insurance.charge.cause

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemCalendarDateBinding
import com.example.fitpet.model.CalendarDate
import com.example.fitpet.ui.insurance.charge.cause.CalendarAdapter.OnItemClickListener

//class CalendarViewHolder(
//    private val binding: ItemCalendarDateBinding,
//    private var selectedPosition: Int,
//    private var onItemClickListener: OnItemClickListener?,
//    private val adapter: CalendarAdapter,
//): RecyclerView.ViewHolder(binding.root) {
//
//    fun onBind(data: CalendarDate) {
//        binding.item = data
//
//        if (selectedPosition == absoluteAdapterPosition) {
//            changeColor(binding, true)
//        } else {
//            changeColor(binding, false)
//        }
//
//        if (onItemClickListener != null) {
//            binding.root.setOnClickListener {
//                onItemClickListener?.onItemClick(data, absoluteAdapterPosition)
//                if (selectedPosition != absoluteAdapterPosition) {
//                    changeColor(binding, true)
//                    adapter.notifyItemChanged(selectedPosition)
//                    selectedPosition = absoluteAdapterPosition
//                }
//            }
//        }
//    }
//
//    private fun changeColor(
//        binding: ItemCalendarDateBinding,
//        selected: Boolean
//    ) {
//        when (selected) {
//            true -> binding.tvItemCalendarDate.setBackgroundResource(R.drawable.bg_oval_filled_point_blue)
//            false -> binding.tvItemCalendarDate.setBackgroundResource(R.drawable.bg_oval_filled_white)
//        }
//    }
//}