package com.example.fitpet.ui.insurance.charge.account

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class InsuranceChargeAccountPageState(
    val accountOwner: StateFlow<String>,
    val accountBank: StateFlow<String>,
    val accountNumber: StateFlow<String>,
    val isBtnEnabled: StateFlow<Boolean>
) : PageState