package com.example.fitpet.ui.insurance.info

import com.example.fitpet.PageState
import com.example.fitpet.ui.model.InsuranceContractInfo
import kotlinx.coroutines.flow.StateFlow
import java.lang.Thread.State

data class InsuranceContractCheckPageState (
    val insuranceContractInfo: StateFlow<InsuranceContractInfo>,
    val company: StateFlow<String>,
    val companyImg: StateFlow<Int>
): PageState