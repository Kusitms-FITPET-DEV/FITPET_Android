package com.example.fitpet.ui.mypet.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemDrawerAlarmBinding
import com.example.fitpet.model.InsuranceAlarm

class MainInsuranceAlarmViewHolder(
    private val binding: ItemDrawerAlarmBinding,
    private val adapter: MainInsuranceAlarmAdapter
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: InsuranceAlarm) {
        binding.item = data

        binding.apply {
            item = data

            root.setOnClickListener {
                adapter.onItemClickListener?.onItemClick(data)
            }
        }
    }
}