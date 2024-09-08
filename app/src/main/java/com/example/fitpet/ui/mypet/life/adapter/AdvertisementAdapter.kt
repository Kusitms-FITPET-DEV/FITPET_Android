package com.example.fitpet.ui.mypet.life.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemAdvertisementBinding
import com.example.fitpet.model.domain.AdvertisementItemVo

class AdvertisementAdapter: ListAdapter<AdvertisementItemVo, RecyclerView.ViewHolder>(
    AdvertisementDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemAdvertisementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdvertisementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is AdvertisementViewHolder -> holder.bind(currentList[position])
        }
    }
}

class AdvertisementDiffCallback: DiffUtil.ItemCallback<AdvertisementItemVo>() {
    override fun areItemsTheSame(
        oldItem: AdvertisementItemVo,
        newItem: AdvertisementItemVo
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AdvertisementItemVo,
        newItem: AdvertisementItemVo
    ): Boolean {
        return oldItem.id == newItem.id
    }
}