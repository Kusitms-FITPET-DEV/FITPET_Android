package com.example.fitpet.ui.mypet.life.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemHospitalRecordBinding
import com.example.fitpet.model.domain.JournalItemVo

class InsuranceRecordAdapter: ListAdapter<JournalItemVo, RecyclerView.ViewHolder>(
    InsuranceItemDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemHospitalRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InsuranceRecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InsuranceRecordViewHolder -> holder.bind(currentList[position])
        }
    }
}

class InsuranceItemDiffCallback: DiffUtil.ItemCallback<JournalItemVo>() {
    override fun areItemsTheSame(oldItem: JournalItemVo, newItem: JournalItemVo): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: JournalItemVo, newItem: JournalItemVo): Boolean {
        return oldItem == newItem
    }
}