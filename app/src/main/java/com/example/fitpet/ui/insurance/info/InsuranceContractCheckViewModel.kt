package com.example.fitpet.ui.insurance.info

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.model.InsuranceContractInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsuranceContractCheckViewModel @Inject constructor(): BaseViewModel<InsuranceContractCheckPageState>() {

    private val _insuranceInfo: MutableStateFlow<InsuranceContractInfo> = MutableStateFlow(
        InsuranceContractInfo(null, null)
    )

    override val uiState = InsuranceContractCheckPageState(
        insuranceContractInfo = _insuranceInfo.asStateFlow()
    )

    fun getInsuranceInfo() {
        // TODO 보험계약 확인 서버통신

        viewModelScope.launch {
            _insuranceInfo.update {
                InsuranceContractInfo(
                    InsuranceContractInfo.Contract(
                        "김이름", "보리", "2024.09.05", "2024.09.05", "3년", 10000, "70%", "자동이체(25일)", "카카오뱅크", "****12341234", "월납"
                    ),
                    InsuranceContractInfo.Coverage(
                        "15만원", "300만원 (연2회)", "1500만원", "O", "O", "O", "O", "X", "1일 보상한도 보장", "1일 보상한도 보장", "1만원", "3000 만원", "15만원 (만8세이상 장례비 제외)"
                    )
                )
            }
        }
    }
}