package com.example.fitpet.ui.mypet.life.hospitalRecord.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.fitpet.databinding.ItemHospitalRecordDetailBinding
import com.example.fitpet.model.response.HospitalRecordItem

class HospitalRecordViewHolder(
    private val binding: ItemHospitalRecordDetailBinding
) : RecyclerView.ViewHolder(binding.root){

    @SuppressLint("SetTextI18n")
    fun bind(item: HospitalRecordItem) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(item.profileUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(binding.imgProfile)
            Glide.with(binding.root.context)
                .load(item.imageUrls[0])
                .apply(RequestOptions().transform(RoundedCorners(4)))
                .into(binding.img1)
            Glide.with(binding.root.context)
                .load(item.imageUrls[1])
                .apply(RequestOptions().transform(RoundedCorners(4)))
                .into(binding.img2)
            Glide.with(binding.root.context)
                .load(item.imageUrls[2])
                .apply(RequestOptions().transform(RoundedCorners(4)))
                .into(binding.img3)

            insuranceTitle.text = item.insuranceCompany + " / " + item.insuranceName
            date.text = item.date
            recordContent.text = item.content

        }
    }
}