package com.example.fitpet.base

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.fitpet.R

@BindingAdapter("bindInsuranceChargeTextViewStyle")
fun TextView.bindInsuranceChargeTextViewStyle(isSelected: Boolean) {
    when (isSelected) {
        true -> this.setTextAppearance(R.style.b4_S)
        false -> this.setTextAppearance(R.style.b4_M)
    }
}