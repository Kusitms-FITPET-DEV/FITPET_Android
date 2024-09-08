package com.example.fitpet.ui.mypet.life.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.fitpet.R
import com.example.fitpet.databinding.ItemHospitalRecordBinding
import com.example.fitpet.model.domain.JournalItemVo

class InsuranceRecordViewHolder(
    private val binding: ItemHospitalRecordBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: JournalItemVo) {
        binding.apply {
            review.text = item.shortContent
            when(item.insuranceCompany) {
                "DB손해보험" -> imgInsurance.setImageResource(R.drawable.ic_db)
                "KB손해보험" -> imgInsurance.setImageResource(R.drawable.ic_kb)
            }
            Glide.with(binding.img.context)
                .load(item.imageUrl)
                .apply(RequestOptions().transform(RoundedCorners(12)))
                .into(img)
        }
    }
}