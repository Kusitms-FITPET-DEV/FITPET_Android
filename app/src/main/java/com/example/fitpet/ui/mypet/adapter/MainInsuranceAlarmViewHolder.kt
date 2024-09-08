package com.example.fitpet.ui.mypet.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemDrawerAlarmBinding
import com.example.fitpet.model.InsuranceAlarm

class MainInsuranceAlarmViewHolder(
    private val binding: ItemDrawerAlarmBinding
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: InsuranceAlarm) {
        binding.item = data
    }
}