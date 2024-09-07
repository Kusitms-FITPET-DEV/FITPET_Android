package com.example.fitpet.ui.mypet.life.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemAdvertisementBinding
import com.example.fitpet.model.domain.AdvertisementItemVo

class AdvertisementViewHolder(
    private val binding: ItemAdvertisementBinding,
    private val listener: AdvertisementAdapter.OnItemChange
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AdvertisementItemVo) {
        binding.apply {
            image.setImageResource(item.imageResource)
            listener.onItemChange(item.id)
        }
    }
}