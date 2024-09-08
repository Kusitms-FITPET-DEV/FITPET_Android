package com.example.fitpet.ui.mypet.life.faq.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpet.databinding.ItemFaqBinding
import com.example.fitpet.model.response.FaqItemVo

class FaqAdapter: ListAdapter<FaqItemVo, RecyclerView.ViewHolder>(
    FaqDIffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemFaqBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FaqViewHolder -> holder.bind(currentList[position])
        }
    }
}

class FaqDIffCallback: DiffUtil.ItemCallback<FaqItemVo>() {
    override fun areItemsTheSame(oldItem: FaqItemVo, newItem: FaqItemVo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FaqItemVo, newItem: FaqItemVo): Boolean {
        return oldItem.question == newItem.question
    }
}