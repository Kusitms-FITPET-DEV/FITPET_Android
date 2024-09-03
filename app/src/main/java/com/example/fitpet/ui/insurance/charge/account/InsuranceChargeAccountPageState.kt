package com.example.fitpet.ui.insurance.charge.account

import com.example.fitpet.PageState
import kotlinx.coroutines.flow.MutableStateFlow

data class InsuranceChargeAccountPageState(
    val accountOwner: MutableStateFlow<String>,
    val accountNumber: MutableStateFlow<String>
) : PageState