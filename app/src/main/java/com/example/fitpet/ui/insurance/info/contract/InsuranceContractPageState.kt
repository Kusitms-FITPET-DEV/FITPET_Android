package com.example.fitpet.ui.insurance.info.contract

import com.example.fitpet.PageState
import com.example.fitpet.ui.model.InsuranceContractInfo
import kotlinx.coroutines.flow.StateFlow

data class InsuranceContractPageState (
    val contractData: StateFlow<InsuranceContractInfo.Contract>,
): PageState