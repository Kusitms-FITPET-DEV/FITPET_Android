package com.example.fitpet.ui.onboarding.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemOnboardingBinding
import com.example.fitpet.model.OnBoardingItemVo

class OnBoardingViewHolder(
    private val binding: ItemOnboardingBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : OnBoardingItemVo) {
        binding.apply {
            titleText.text = item.title
            contentText.text = item.content
            onBoardingImage.setBackgroundResource(item.img)
        }
    }
}