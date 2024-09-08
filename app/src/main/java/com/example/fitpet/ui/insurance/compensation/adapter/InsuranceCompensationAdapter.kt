package com.example.fitpet.ui.insurance.compensation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fitpet.databinding.ItemInsuranceCompensationBinding
import com.example.fitpet.model.CompensationListItem
import com.example.fitpet.util.ItemDiffCallback

class InsuranceCompensationAdapter: ListAdapter<CompensationListItem, InsuranceCompensationViewHolder>(
    ItemDiffCallback<CompensationListItem>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.id == newItem.id }
    )
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InsuranceCompensationViewHolder {
        val binding = ItemInsuranceCompensationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InsuranceCompensationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InsuranceCompensationViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}