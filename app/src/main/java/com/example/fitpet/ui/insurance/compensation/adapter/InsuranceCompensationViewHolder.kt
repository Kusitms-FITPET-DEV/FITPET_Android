package com.example.fitpet.ui.insurance.compensation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemInsuranceCompensationBinding
import com.example.fitpet.model.CompensationListItem

class InsuranceCompensationViewHolder(
    private val binding: ItemInsuranceCompensationBinding
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: CompensationListItem) {
        binding.item = data
    }
}