package com.example.fitpet.ui.insurance.info.contract

import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.ui.model.InsuranceContractInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InsuranceInfoContractViewModel @Inject constructor(): BaseViewModel<InsuranceContractPageState>() {

    private val _contractData: MutableStateFlow<InsuranceContractInfo.Contract> = MutableStateFlow(
        InsuranceContractInfo.Contract())

    override val uiState = InsuranceContractPageState(
        contractData = _contractData.asStateFlow()
    )

    fun setContractData(contractData: InsuranceContractInfo.Contract) {
        _contractData.update { contractData }
    }
}