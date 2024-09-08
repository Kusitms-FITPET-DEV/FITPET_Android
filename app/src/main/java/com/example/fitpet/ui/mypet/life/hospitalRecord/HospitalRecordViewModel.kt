package com.example.fitpet.ui.mypet.life.hospitalRecord

import androidx.lifecycle.viewModelScope
import com.example.fitpet.base.BaseViewModel
import com.example.fitpet.data.repository.PetLifeRepository
import com.example.fitpet.model.response.HospitalRecordItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalRecordViewModel @Inject constructor(
    private val petLifeRepository: PetLifeRepository
) : BaseViewModel<HospitalRecordPageState>() {
    private val recordListStateFlow: MutableStateFlow<List<HospitalRecordItem>> = MutableStateFlow(emptyList())

    override val uiState = HospitalRecordPageState(
        recordList = recordListStateFlow.asStateFlow()
    )

    init {
        getRecordList()
    }

    private fun getRecordList() {
        viewModelScope.launch {
            petLifeRepository.getHospitalRecordList().collect {
                resultResponse(it, ::onSuccessGetRecordList)
            }
        }
    }

    private fun onSuccessGetRecordList(list: List<HospitalRecordItem>) {
        viewModelScope.launch {
            recordListStateFlow.update { list }
        }
    }
}