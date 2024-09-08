package com.example.fitpet.base

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.fitpet.R
import com.example.fitpet.ui.insurance.compensation.InsuranceCompensationViewModel.Companion.PROGRESS_AFTER
import com.example.fitpet.ui.insurance.compensation.InsuranceCompensationViewModel.Companion.PROGRESS_BEFORE
import com.example.fitpet.ui.insurance.compensation.InsuranceCompensationViewModel.Companion.PROGRESS_CURRENT

@BindingAdapter("bindInsuranceChargeTextViewStyle")
fun TextView.bindInsuranceChargeTextViewStyle(isSelected: Boolean) {
    when (isSelected) {
        true -> this.setTextAppearance(R.style.b4_S)
        false -> this.setTextAppearance(R.style.b4_M)
    }
}

@BindingAdapter("bindInsuranceCompensationTextViewStyle")
fun TextView.bindInsuranceCompensationTextViewStyle(isSelected: Boolean) {
    when (isSelected) {
        true -> this.setTextAppearance(R.style.b4_M)
        false -> this.setTextAppearance(R.style.b4)
    }
}

@BindingAdapter("bindInsuranceCompensationProgress")
fun ImageView.bindInsuranceCompensationProgress(progressId: Int) {
    when (progressId) {
        PROGRESS_BEFORE -> this.setImageResource(R.drawable.ic_progress_before)
        PROGRESS_CURRENT -> this.setImageResource(R.drawable.ic_progress_point)
        PROGRESS_AFTER -> this.setImageResource(R.drawable.ic_progress_after)
    }
}

@BindingAdapter("setImageUri")
fun setImageUri(imageView: ImageView, imageUri: String) {
    Glide.with(imageView.context)
        .load(imageUri.toUri())
        .into(imageView)
}