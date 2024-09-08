package com.example.fitpet.ui.insurance.compensation

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.InsuranceDetailRepository
import com.example.fitpet.model.CompensationListItem
import com.example.fitpet.model.response.CompensationResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsuranceCompensationViewModel @Inject constructor(
    private val insuranceDetailRepository: InsuranceDetailRepository
): BaseViewModel<InsuranceCompensationPageState>() {

    private val _petName: MutableStateFlow<String> = MutableStateFlow("")
    private val _startDate: MutableStateFlow<String> = MutableStateFlow("")
    private val _endDate: MutableStateFlow<String> = MutableStateFlow("")
    private val _compensationItemList: MutableStateFlow<List<CompensationListItem>> = MutableStateFlow(emptyList())

    override val uiState = InsuranceCompensationPageState(
        petName = _petName.asStateFlow(),
        startDate = _startDate.asStateFlow(),
        endDate = _endDate.asStateFlow(),
        compensationItemList = _compensationItemList.asStateFlow()
    )

    fun onClickBackBtn() {
        emitEventFlow(InsuranceCompensationEvent.GoBackPage)
    }

    fun getCompensationList(petId: Int) {
        viewModelScope.launch {
            insuranceDetailRepository.getInsuranceCompensation(petId).collect { result ->
                resultResponse(result, { data ->
                    updateCompensationData(data)
                })
            }
        }
    }

    private fun updateCompensationData(data: CompensationResponse) {
        _petName.update { data.petName }
        _startDate.update { data.startDate }
        _endDate.update { data.endDate }
        _compensationItemList.update { setItemList(data) }
    }

    private fun setItemList(data: CompensationResponse): List<CompensationListItem> {
        val tempList = ArrayList<CompensationListItem>()
        for (i in 0 until data.compensationlist.size) {
            with (data.compensationlist[i]) {
                tempList.add(CompensationListItem(i, compensationType, progress, applyDate, chargePerson, receiveMoney, setProgressFirstItem(progress), setProgressSecondItem(progress), setProgressThirdItem(progress), setProgressFourthItem(progress)))
            }
        }

        return tempList
    }

    private fun setProgressFirstItem(progress: String): Int {
        when (progress) {
            PROGRESS_FIRST -> return PROGRESS_CURRENT
            else -> return PROGRESS_BEFORE
        }
    }
    private fun setProgressSecondItem(progress: String): Int {
        when (progress) {
            PROGRESS_FIRST -> return PROGRESS_AFTER
            PROGRESS_SECOND -> return PROGRESS_CURRENT
            else -> return PROGRESS_BEFORE
        }
    }
    private fun setProgressThirdItem(progress: String): Int {
        when (progress) {
            PROGRESS_THIRD -> return PROGRESS_CURRENT
            PROGRESS_FOURTH -> return PROGRESS_BEFORE
            else -> return PROGRESS_AFTER
        }
    }
    private fun setProgressFourthItem(progress: String): Int {
        when (progress) {
            PROGRESS_FOURTH -> return PROGRESS_CURRENT
            else -> return PROGRESS_AFTER
        }
    }


    companion object {
        const val PROGRESS_BEFORE = 0
        const val PROGRESS_CURRENT = 1
        const val PROGRESS_AFTER = 2
        const val PROGRESS_FIRST = "접수"
        const val PROGRESS_SECOND = "배정"
        const val PROGRESS_THIRD = "심사"
        const val PROGRESS_FOURTH = "완료"
    }
}