package com.example.fitpet.ui.insurance.charge.cause

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeCausePageState (
    val selectedCause: StateFlow<String>,
    val currentDate: StateFlow<String>,
    val selectedDate: StateFlow<String>,
    val isBtnEnabled: StateFlow<Boolean>
): PageState