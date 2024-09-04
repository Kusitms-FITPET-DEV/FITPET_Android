package com.example.fitpet.ui.mypet.insurance.nopet

import androidx.transition.Visibility
import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceNoPetPageState(
    val temp: String = ""
): PageState