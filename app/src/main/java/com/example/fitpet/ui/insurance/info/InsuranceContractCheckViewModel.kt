package com.example.fitpet.ui.insurance.info

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.InsuranceDetailRepository
import com.example.fitpet.model.response.InsuranceDetailResponse
import com.example.fitpet.ui.model.InsuranceContractInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InsuranceContractCheckViewModel @Inject constructor(
    private val insuranceDetailRepository: InsuranceDetailRepository
): BaseViewModel<InsuranceContractCheckPageState>() {

    private val _insuranceInfo: MutableStateFlow<InsuranceContractInfo> = MutableStateFlow(
        InsuranceContractInfo(null, null)
    )

    override val uiState = InsuranceContractCheckPageState(
        insuranceContractInfo = _insuranceInfo.asStateFlow()
    )

    fun getInsuranceInfo(petId: Int) {
        viewModelScope.launch {
            insuranceDetailRepository.getInsuranceDetail(petId).collect { result ->
                resultResponse(result, { data ->
                    Timber.d("[테스트] 보험 계약 확인 -> $data")
                    updateInsuranceContractData(data)
                })
            }
        }

//        viewModelScope.launch {
//            _insuranceInfo.update {
//                InsuranceContractInfo(
//                    InsuranceContractInfo.Contract(
//                        "김이름", "보리", "2024.09.05", "2024.09.05", "3년", 10000, "70%", "자동이체(25일)", "카카오뱅크", "****12341234", "월납"
//                    ),
//                    InsuranceContractInfo.Coverage(
//                        "15만원", "300만원 (연2회)", "1500만원", "O", "O", "O", "O", "X", "1일 보상한도 보장", "1일 보상한도 보장", "1만원", "3000 만원", "15만원 (만8세이상 장례비 제외)"
//                    )
//                )
//            }
//        }
    }

    private fun updateInsuranceContractData(data: InsuranceDetailResponse) {
        _insuranceInfo.update {
            InsuranceContractInfo(
                InsuranceContractInfo.Contract(data.contract.contractor, data.contract.insurant, data.contract.startDate, data.contract.endDate, data.contract.updateCycle, data.contract.insuranceFee, data.contract.priceRate, data.contract.payWay, data.contract.bank, data.contract.bankAccount, data.contract.payCycle),
                InsuranceContractInfo.Coverage(data.coverage.dailyTreatLimit, data.coverage.dailySurgeryCostLimit, data.coverage.yearReward, data.coverage.hipJointLimit, data.coverage.skinDisease, data.coverage.skinEtc, data.coverage.dentalCoverage, data.coverage.inspectionCoverage, data.coverage.foreignObjectRemoval, data.coverage.selfBurden, data.coverage.compensationLiability, data.coverage.funeralAid)
            )
        }
    }

    fun onClickBackBtn() {
        emitEventFlow(InsuranceContractCheckEvent.GoBackPage)
    }

    companion object {
        const val CONTRACT = "CONTRACT"
        const val COVERAGE = "COVERAGE"
    }
}