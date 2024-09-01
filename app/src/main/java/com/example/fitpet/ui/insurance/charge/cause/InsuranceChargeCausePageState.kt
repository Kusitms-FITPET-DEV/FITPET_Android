package com.example.fitpet.ui.insurance.charge.cause

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeCausePageState (
    val selectedDate: StateFlow<String>
): PageState