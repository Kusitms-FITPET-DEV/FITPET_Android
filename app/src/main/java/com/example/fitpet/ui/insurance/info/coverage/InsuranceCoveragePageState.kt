package com.example.fitpet.ui.insurance.info.coverage

import com.example.fitpet.PageState
import com.example.fitpet.ui.model.InsuranceContractInfo
import kotlinx.coroutines.flow.StateFlow

data class InsuranceCoveragePageState (
    val coverageData: StateFlow<InsuranceContractInfo.Coverage>
): PageState