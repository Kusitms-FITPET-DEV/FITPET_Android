package com.example.fitpet.ui.insurance.info

import androidx.lifecycle.viewModelScope
import com.example.fitpet.R
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
    private val _company: MutableStateFlow<String> = MutableStateFlow("")
    private val _companyImg: MutableStateFlow<Int> = MutableStateFlow(0)

    override val uiState = InsuranceContractCheckPageState(
        insuranceContractInfo = _insuranceInfo.asStateFlow(),
        company = _company.asStateFlow(),
        companyImg = _companyImg.asStateFlow()
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
    }

    fun getCompany(getCompany: String) {
        _company.update { getCompany }
        _companyImg.update {
            when (getCompany) {
                "DB손해보험" -> R.drawable.ic_db_v3
                "KB손해보험" -> R.drawable.ic_kb_v3
                "현대해상" ->  R.drawable.ic_hyundai_v3
                "삼성화재" ->  R.drawable.ic_samsung_v3
                "메리츠" -> R.drawable.ic_meritz_v3
                else -> R.drawable.ic_db_v3
            }
        }
    }

    private fun updateInsuranceContractData(data: InsuranceDetailResponse) {
        _insuranceInfo.update {
            InsuranceContractInfo(
                InsuranceContractInfo.Contract(data.contract.contractor, data.contract.insurant, data.contract.startDate, data.contract.endDate, data.contract.updateCycle, data.contract.insuranceFee, data.contract.priceRate, data.contract.payWay, data.contract.bank, data.contract.bankAccount, data.contract.payCycle),
                InsuranceContractInfo.Coverage(data.coverage.dailyTreatLimit, data.coverage.dailySurgeryCostLimit, data.coverage.yearReward, data.coverage.hipJointLimit, data.coverage.skinDisease, data.coverage.skinEtc, data.coverage.oralCoverage, data.coverage.dentalCoverage, data.coverage.inspectionCoverage, data.coverage.foreignObjectRemoval, data.coverage.selfBurden, data.coverage.compensationLiability, data.coverage.funeralAid)
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