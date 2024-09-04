package com.example.fitpet.ui.insurance.charge.contact.agree

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.StateFlow

data class InsuranceAgreePageState (
    val isSelectedAll: StateFlow<Boolean>,
    val isSelectedEssential: StateFlow<Boolean>,
    val isSelectedChoice: StateFlow<Boolean>,
): PageState