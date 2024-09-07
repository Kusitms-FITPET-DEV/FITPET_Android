package com.example.fitpet.ui.mypet.life.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemAdvertisementBinding
import com.example.fitpet.model.domain.AdvertisementItemVo

class AdvertisementViewHolder(
    private val binding: ItemAdvertisementBinding,
    private val listener: AdvertisementAdapter.OnItemChange
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AdvertisementItemVo) {
        binding.apply {
            image.setImageResource(item.imageResource)

            when(item.id) {
                1L -> bgFrame.setBackgroundResource(R.drawable.bg_rectangle_filled_gradient_orange_radius_12)
                2L -> bgFrame.setBackgroundResource(R.drawable.bg_rectangle_filled_gradient_blue_radius_12)
                3L -> bgFrame.setBackgroundResource(R.drawable.bg_rectangle_filled_gradient_green_radius_12)
            }
            listener.onItemChange(item.id)
        }
    }
}