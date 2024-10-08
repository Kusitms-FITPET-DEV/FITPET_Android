package com.example.fitpet.ui.mypet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fitpet.databinding.ItemDrawerAlarmBinding
import com.example.fitpet.model.InsuranceAlarm
import com.example.fitpet.util.ItemDiffCallback

class MainInsuranceAlarmAdapter: ListAdapter<InsuranceAlarm, MainInsuranceAlarmViewHolder>(
    ItemDiffCallback<InsuranceAlarm>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.historyId == newItem.historyId }
    )
) {

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: InsuranceAlarm)
    }

    fun setOnClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainInsuranceAlarmViewHolder {
        val binding = ItemDrawerAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainInsuranceAlarmViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: MainInsuranceAlarmViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}