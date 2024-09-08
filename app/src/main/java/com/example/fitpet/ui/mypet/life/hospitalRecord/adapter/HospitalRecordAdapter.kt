package com.example.fitpet.ui.mypet.life.hospitalRecord.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemHospitalRecordDetailBinding
import com.example.fitpet.model.response.HospitalRecordItem

class HospitalRecordAdapter: ListAdapter<HospitalRecordItem, RecyclerView.ViewHolder>(
    HospitalRecordItemDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemHospitalRecordDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HospitalRecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HospitalRecordViewHolder -> holder.bind(currentList[position])
        }
    }

}

class HospitalRecordItemDiffCallback: DiffUtil.ItemCallback<HospitalRecordItem>() {
    override fun areItemsTheSame(
        oldItem: HospitalRecordItem,
        newItem: HospitalRecordItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: HospitalRecordItem,
        newItem: HospitalRecordItem
    ): Boolean {
        return oldItem.journalId == newItem.journalId
    }

}