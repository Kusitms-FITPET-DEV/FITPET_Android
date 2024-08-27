package com.example.fitpet.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemOnboardingBinding
import com.example.fitpet.model.OnBoardingItemVo

class OnBoardingAdapter: ListAdapter<OnBoardingItemVo, RecyclerView.ViewHolder>(
    OnBoardingItemDiffCallBack()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OnBoardingViewHolder -> holder.bind(currentList[position])
        }
    }
}

class OnBoardingItemDiffCallBack : DiffUtil.ItemCallback<OnBoardingItemVo>() {
    override fun areItemsTheSame(oldItem: OnBoardingItemVo, newItem: OnBoardingItemVo): Boolean = oldItem.title == newItem.title
    override fun areContentsTheSame(oldItem: OnBoardingItemVo, newItem: OnBoardingItemVo): Boolean = oldItem == newItem
}