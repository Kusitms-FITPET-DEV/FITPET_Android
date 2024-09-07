package com.example.fitpet.ui.insurance.info.coverage

import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.ui.model.InsuranceContractInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InsuranceInfoCoverageViewModel @Inject constructor(): BaseViewModel<InsuranceCoveragePageState>() {

    private val _coverageData: MutableStateFlow<InsuranceContractInfo.Coverage> = MutableStateFlow(
        InsuranceContractInfo.Coverage())

    override val uiState = InsuranceCoveragePageState(
        coverageData = _coverageData.asStateFlow()
    )

    fun setCoverageData(coverageData: InsuranceContractInfo.Coverage) {
        _coverageData.update { coverageData }
    }
}