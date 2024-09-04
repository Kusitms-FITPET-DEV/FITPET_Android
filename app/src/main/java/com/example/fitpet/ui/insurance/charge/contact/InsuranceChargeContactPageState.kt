package com.example.fitpet.ui.insurance.charge.contact

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeContactPageState (
    val isSelectedKaKao: StateFlow<Boolean>,
    val isSelectedEmail: StateFlow<Boolean>,
    val isBtnEnabled: StateFlow<Boolean>
): PageState