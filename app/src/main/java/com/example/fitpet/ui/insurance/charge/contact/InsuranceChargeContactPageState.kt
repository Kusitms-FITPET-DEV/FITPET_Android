package com.example.fitpet.ui.insurance.charge.contact

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeContactPageState(
    val isSelectedKaKao: StateFlow<Boolean>,
    val isSelectedEmail: StateFlow<Boolean>,
    val emailInput: StateFlow<String>,
    val isBtnEnabled: StateFlow<Boolean>,
    val isClickedAgreement: StateFlow<Boolean>,
    val isOptionAgree: StateFlow<Boolean>
) : PageState