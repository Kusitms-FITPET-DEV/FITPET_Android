package com.example.fitpet.base

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.fitpet.R

@BindingAdapter("bindInsuranceChargeTextViewStyle")
fun TextView.bindInsuranceChargeTextViewStyle(isSelected: Boolean) {
    when (isSelected) {
        true -> this.setTextAppearance(R.style.b4_S)
        false -> this.setTextAppearance(R.style.b4_M)
    }
}

@BindingAdapter("setImageUri")
fun setImageUri(imageView: ImageView, imageUri: String) {
    Glide.with(imageView.context)
        .load(imageUri.toUri())
        .into(imageView)
}