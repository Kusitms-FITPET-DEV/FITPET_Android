package com.example.fitpet.ui.insurance.info

import com.example.fitpet.PageState
import com.example.fitpet.model.InsuranceContractInfo
import kotlinx.coroutines.flow.StateFlow

data class InsuranceContractCheckPageState (
    val insuranceContractInfo: StateFlow<InsuranceContractInfo>
): PageState